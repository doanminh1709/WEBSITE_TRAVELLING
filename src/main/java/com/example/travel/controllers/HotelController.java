package com.example.travel.controllers;

import com.example.travel.dtos.HotelDTO;
import com.example.travel.services.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/hotels")
@CrossOrigin("http://localhost:3000")
public class HotelController {
    @Autowired
    private IHotelService iHotelService;

    @GetMapping
    public ResponseEntity<?> getAllHotel() {
        return ResponseEntity.status(200).body(iHotelService.getAllHotel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iHotelService.getHotelById(id));
    }

    @PostMapping("/{locationId}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
//    public ResponseEntity<?> createHotel(@RequestBody @Valid HotelDTO hotelDTO,
//                                         @PathVariable("locationId") Long locationId) {
//        return ResponseEntity.status(201).body(iHotelService.createHotel(hotelDTO, locationId));
//    }

    @PatchMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> editHotelById(@PathVariable("id") Long id, @RequestBody @Valid HotelDTO hotelDTO){
        return ResponseEntity.status(201).body(iHotelService.editHotelById(id, hotelDTO));
    }


    @DeleteMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deleteHotelById(@PathVariable("id") Long id) {
        System.out.println(1);
        iHotelService.deleteHotelById(id);
        System.out.println(2);
        return ResponseEntity.status(200).body("Delete Success");
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getImgByHotelId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iHotelService.getImgByHotelId(id));
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<?> getRoomByHotelId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iHotelService.getRoomByHotelId(id));
    }
}
