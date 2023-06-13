package com.example.travel.controllers;

import com.example.travel.dtos.CommentDTO;
import com.example.travel.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin("http://localhost:3000")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(commentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(commentService.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editCmt(@RequestPart @Valid CommentDTO commentDTO,
                                     @PathVariable("id") Long id,
                                     @RequestParam("files") MultipartFile[] files,
                                     HttpServletRequest httpServletRequest, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        return ResponseEntity.status(201).body(commentService.editCmt(commentDTO, id, files, httpServletRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCmt(@PathVariable("id") Long id) throws IOException {
        commentService.deleteCmtById(id);
        return ResponseEntity.status(201).body("Delete success");
    }

}
