package com.example.travel.services;

import com.example.travel.daos.Comment;
import com.example.travel.daos.Post;
import com.example.travel.dtos.CommentDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> getAll();
    Optional<Post> getPostById(Long id);
    List<Post> getAllByUser(String role);
    Post editPost(Long id, String title, MultipartFile file) throws IOException;
    Post createPost( String title, Long locationId, MultipartFile file, HttpServletRequest httpServletRequest) throws IOException;
    Comment createCmt(CommentDTO commentDTO, Long postId, MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException;
}
