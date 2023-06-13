package com.example.travel.controllers;

import com.example.travel.services.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/imgs")
@CrossOrigin("http://localhost:3000")
public class ImgController {
    @Autowired
    private IImageService iImageService;

    @GetMapping
    public ResponseEntity<?> getAllImg() {
        return ResponseEntity.status(200).body(iImageService.getAllImage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImgById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iImageService.getImageById(id));
    }

    @PostMapping("/createImgLocation")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createImgLocation(@RequestParam("locationId") Long locationId,
                                               @RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.status(201).body(iImageService.createImgLocation(locationId, files));
    }

    @PostMapping("/createImgHotel")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createImgHotel(@RequestParam("locationId") Long locationId,
                                               @RequestParam("hotelId") Long hotelId,
                                               @RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.status(201).body(iImageService.createImgHotel(locationId, hotelId, files));
    }

    @PostMapping("/createImgRoom")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createImgRoom(@RequestParam("locationId") Long locationId,
                                           @RequestParam("hotelId") Long hotelId,
                                           @RequestParam("roomId") Long roomId,
                                               @RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.status(201).body(iImageService.createImgRoom(locationId, hotelId, roomId, files));
    }


    @DeleteMapping
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deleteImg(@PathVariable("id") Long id) throws IOException {
        iImageService.deleteImg(id);
        return ResponseEntity.status(200).body("Delete Success");
    }
}
