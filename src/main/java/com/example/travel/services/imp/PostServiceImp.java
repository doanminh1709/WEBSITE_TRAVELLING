package com.example.travel.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.travel.daos.*;
import com.example.travel.dtos.CommentDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.*;
import com.example.travel.services.IMailService;
import com.example.travel.services.IPostService;
import com.example.travel.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.travel.constants.RoleConstant.ROLE_USER;

@Service
public class PostServiceImp implements IPostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private IMailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getAllByUser(String role) {
        List<Post> posts = postRepository.findAll();
        List<Post> postsByRole = new ArrayList<>();

        for (Post post: posts) {
            if (post.getUser().getRole().equals(ROLE_USER)) {
                postsByRole.add(post);
            }
        }

        return postsByRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Post createPost( String title, Long locationId, MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        User requestedUser = (User) httpServletRequest.getAttribute("user");
        Optional<Location> location = locationRepository.findById(locationId);
        if(location.isEmpty()) {
            throw new NotFoundException("Location not found");
        }
        User userOld = userRepository.findByUsername(requestedUser.getUsername());
        Post post = new Post();
        post.setUser(userOld);
        post.setLocation(location.get());
        post.setTitle(title);
        Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Image image = new Image();
        image.setPost(post);
        image.setLinkImg(cloudinaryMap.get("secure_url").toString());
        image.setPublicId(cloudinaryMap.get("public_id").toString());
        Image newImg = imageRepository.save(image);
        post.setAvatar(newImg.getLinkImg());
        post.setDateAdd(java.time.LocalDate.now().toString());

        List<User> users = userRepository.findAll();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            try {
                for (User user:
                     users) {
                    mailService.send("Thông báo", "HELLO TRAVEL Có bài viết mới", user.getEmail(), true);
                }

            } catch (MessagingException ignored) {
            }
        });

        return postRepository.save(post);
    }

    @Override
    public Comment createCmt(CommentDTO commentDTO, Long postId, MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException {
        User requestedUser = (User) httpServletRequest.getAttribute("user");
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        User user = userRepository.findByUsername(requestedUser.getUsername());
        Comment comment = Convert.fromCommentDTOToComment(commentDTO);
        comment.setPost(post.get());
        comment.setUser(user);
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            Image image = new Image();
            image.setComment(comment);
            image.setLinkImg(cloudinaryMap.get("secure_url").toString());
            image.setPublicId(cloudinaryMap.get("public_id").toString());
            Image newImg = imageRepository.save(image);
            images.add(newImg);
        }
        comment.setImages(images);

        return commentRepository.save(comment);
    }

    @Override
    public Post editPost(Long id,  String title, MultipartFile file) throws IOException {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        Post newPost = post.get();
        newPost.setTitle(title);

        cloudinary.uploader().destroy(newPost.getImage().getPublicId(), ObjectUtils.emptyMap());

        Map<?, ?> cloudinaryMap = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Image image = new Image();
        image.setPost(newPost);
        image.setLinkImg(cloudinaryMap.get("secure_url").toString());
        image.setPublicId(cloudinaryMap.get("public_id").toString());
        Image newImg = imageRepository.save(image);
        newPost.setAvatar(newImg.getLinkImg());
        return postRepository.save(newPost);
    }

}
