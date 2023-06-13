package com.example.travel.controllers;

import com.example.travel.daos.Post;
import com.example.travel.dtos.CommentDTO;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.repositories.PostRepository;
import com.example.travel.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin("http://localhost:3000")
public class PostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(postService.getPostById(id));
    }

    @GetMapping("/getByRole/{role}")
    public ResponseEntity<?> getAllByRole(@PathVariable("role") String role) {
        return ResponseEntity.status(200).body(postService.getAllByUser(role));
    }

    @PostMapping
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createPost(@RequestParam("title")  String title,
                                        @RequestParam("locationId") Long locationId,
                                        @RequestParam("file") MultipartFile file,
                                        HttpServletRequest httpServletRequest) throws IOException {
        return ResponseEntity.status(201).body(postService.createPost(title, locationId, file, httpServletRequest));
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> editPost(@PathVariable("id") Long id,
                                      @RequestParam("title")  String title,
                                      @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(201).body(postService.editPost(id, title, file));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new NotFoundException("Post not found");
        }
        postRepository.deleteById(id);
        return ResponseEntity.status(201).body("Delete success");
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> createCmt(@RequestPart @Valid CommentDTO commentDTO,
                                       @PathVariable("postId") Long postId,
                                       @RequestParam("files") MultipartFile[] files,
                                       HttpServletRequest httpServletRequest,
                                       BindingResult  bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        return ResponseEntity.status(201).body(postService.createCmt(commentDTO, postId, files, httpServletRequest));
    }
}
