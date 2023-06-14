package com.example.travel.controllers;

import com.example.travel.services.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("http://localhost:3000")
public class VideoController {
    @Autowired
    private IVideoService iVideoService;

    @GetMapping
    public ResponseEntity<?> getAllVideo() {
        return ResponseEntity.status(200).body(iVideoService.getAllVideo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iVideoService.getVideoById(id));
    }

    @PostMapping
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createVideo(@RequestParam("locationId") Long locationId,
                                       @RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.status(201).body(iVideoService.createVideo(locationId, files));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deleteVideo(@PathVariable("id") Long id) {
        iVideoService.deleteVideo(id);
        return ResponseEntity.status(200).body("Delete Success");
    }
}
