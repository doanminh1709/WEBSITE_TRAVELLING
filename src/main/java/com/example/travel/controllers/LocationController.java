package com.example.travel.controllers;

import com.example.travel.dtos.LocationDTO;
import com.example.travel.services.ILocationService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/locations")
@CrossOrigin("http://localhost:3000")
public class LocationController {
    @Autowired
    private ILocationService iLocationService;

    @GetMapping
    public ResponseEntity<?> getAllLocation() {
        return ResponseEntity.status(200).body(iLocationService.getAllLocation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iLocationService.getLocationById(id));
    }

//    @PostMapping
////   @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
//    public ResponseEntity<?> createLocation(@RequestBody @Valid LocationDTO locationDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
//        }
//        return ResponseEntity.status(201).body(iLocationService.createLocation(locationDTO));
//    }
    @PostMapping
//   @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> createLocation(@RequestBody @Valid LocationDTO locationDTO) {
        return ResponseEntity.status(201).body(iLocationService.createLocation(locationDTO));
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> editLocationById(@PathVariable("id") Long id, @RequestBody @Valid LocationDTO locationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        return ResponseEntity.status(201).body(iLocationService.editLocation(id, locationDTO));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deleteLocationById(@PathVariable("id") Long id) {
        iLocationService.deleteLocation(id);
        return ResponseEntity.status(200).body("Delete Success");
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<?> getImgByLocationId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iLocationService.getImgByLocationId(id));
    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<?> getVideoByLocationId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iLocationService.getVideoByLocationId(id));
    }

    @GetMapping("/{id}/hotels")
    public ResponseEntity<?> getHotelsByLocationId(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iLocationService.getHotelByLocationId(id));
    }
}
