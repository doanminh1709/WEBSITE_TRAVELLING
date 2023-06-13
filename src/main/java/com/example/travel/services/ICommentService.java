package com.example.travel.services;

import com.example.travel.daos.Comment;
import com.example.travel.daos.Image;
import com.example.travel.dtos.CommentDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comment> getAll();
    Optional<Comment> getById(Long id);
    Comment editCmt(CommentDTO commentDTO, Long id, MultipartFile[] files, HttpServletRequest httpServletRequest) throws IOException;
    void deleteCmtById(Long id) throws IOException;
}
