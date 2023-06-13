package com.example.travel.controllers;

import com.example.travel.dtos.CommentDTO;
import com.example.travel.dtos.ServicesDTO;
import com.example.travel.services.IServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@CrossOrigin("http://localhost:3000")
public class ServiceController {
    @Autowired
    private IServicesService iServicesService;

    @GetMapping
    public ResponseEntity<?> getAllService() {
        return ResponseEntity.status(200).body(iServicesService.getAllService());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iServicesService.getServiceById(id));
    }

    @PostMapping("/{userId}/{roomId}")
    public ResponseEntity<?> createService(@RequestBody ServicesDTO servicesDTO,
                                           @PathVariable("userId") Long userId,
                                           @PathVariable("roomId") Long roomId) {
        return ResponseEntity.status(200).body(iServicesService.createService(servicesDTO,roomId,userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editServiceById(@PathVariable("id") Long id, @RequestBody ServicesDTO servicesDTO) {
        return ResponseEntity.status(201).body(iServicesService.editServiceById(id,servicesDTO));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deleteServiceById(@PathVariable("id") Long id) {
        iServicesService.deleteServiceById(id);
        return ResponseEntity.status(200).body("Delete Success");
    }

    @PostMapping("/{serviceId}/comments")
    public ResponseEntity<?> createCmt(@RequestPart @Valid CommentDTO commentDTO,
                                       @PathVariable("serviceId") Long postId,
                                       @RequestParam("files") MultipartFile[] files,
                                       HttpServletRequest httpServletRequest, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        return ResponseEntity.status(201).body(iServicesService.createCmt(commentDTO, postId, files, httpServletRequest));
    }

//    @GetMapping("/{hotelId}/{roomId}")
//    public ResponseEntity<?> getTotalMoney(@PathVariable("hotelId") Long hotelId,
//                                           @PathVariable("roomId") List<Long> roomId) {
//        return ResponseEntity.status(200).body(iServicesService.totalMoney(hotelId, roomId));
//    }
    @GetMapping("{idRoom}/services")
    public ResponseEntity<?> findAllByRoom(@PathVariable("idRoom")Long idRoom){
        return ResponseEntity.status(200).body(iServicesService.getAllServiceByIdRoom(idRoom));
    }
}
