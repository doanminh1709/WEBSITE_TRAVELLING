package com.example.travel.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.travel.daos.Comment;
import com.example.travel.daos.Image;
import com.example.travel.daos.Post;
import com.example.travel.daos.User;
import com.example.travel.dtos.CommentDTO;
import com.example.travel.exceptions.BadRequestException;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.CommentRepository;
import com.example.travel.repositories.ImageRepository;
import com.example.travel.repositories.PostRepository;
import com.example.travel.repositories.UserRepository;
import com.example.travel.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CommentServiceImp implements ICommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment editCmt(CommentDTO commentDTO, Long id, MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException {
        User requestedUser = (User) httpServletRequest.getAttribute("user");
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new NotFoundException("Comment not found");
        }
        User user = userRepository.findByUsername(requestedUser.getUsername());
        if (!comment.get().getUser().getUsername().equals(user.getUsername())) {
            throw new BadRequestException("Editor did not match commenter");
        }

        List<Image> imagesOld = comment.get().getImages();
        for (Image i: imagesOld) {
            cloudinary.uploader().destroy(i.getPublicId(), ObjectUtils.emptyMap());
        }

        Comment newComment = comment.get();
        newComment.setContent(commentDTO.getContent());
        newComment.setRate(commentDTO.getRate());

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setComment(newComment);
            image.setLinkImg(cloudinaryMap.get("secure_url").toString());
            image.setPublicId(cloudinaryMap.get("public_id").toString());
            Image newImg = imageRepository.save(image);
            images.add(newImg);
        }
        newComment.setImages(images);
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteCmtById(Long id) throws IOException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new NotFoundException("Cound not find a comment with id: " + id);
        }
        List<Image> images = comment.get().getImages();
        for (Image i: images) {
            cloudinary.uploader().destroy(i.getPublicId(), ObjectUtils.emptyMap());
        }
        commentRepository.deleteById(id);
    }


}
