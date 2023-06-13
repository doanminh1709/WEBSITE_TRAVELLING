package com.example.travel.controllers;

import com.example.travel.dtos.RoomDTO;
import com.example.travel.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:3000")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @GetMapping
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.status(200).body(roomService.getAllRoom());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImgByRoomId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(roomService.getById(id));

    }

    @PostMapping("/{hotelId}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createRoom(@RequestBody @Valid RoomDTO roomDTO, @PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.status(201).body(roomService.createRoom(roomDTO, hotelId));
    }

    @PatchMapping("/{idHotel}/{idRoom}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> editRoomById(
            @PathVariable("idHotel") Long idHotel,
            @PathVariable("idRoom") Long idRoom,
            @RequestBody @Valid RoomDTO roomDTO
    ) {
        return ResponseEntity.status(201).body(roomService.editRoomById(idHotel, idRoom, roomDTO));
    }

    @DeleteMapping("/{idHotel}/{idRoom}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> editRoomById(
            @PathVariable("idHotel") Long idHotel,
            @PathVariable("idRoom") Long idRoom
    ) {
        roomService.deleteRoomById(idHotel, idRoom);
        return ResponseEntity.status(200).body("Delete Success");
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getImgRoomId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(roomService.getImgByRoomId(id));
    }
}
