package com.example.travel.controllers;

import com.example.travel.daos.User;
import com.example.travel.dtos.UserChangePasswordDTO;
import com.example.travel.dtos.UserDTO;
import com.example.travel.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private IUserService userService;

//    @PostMapping
//    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
//        }
//        return ResponseEntity.ok(userService.createNewUser(userDTO));
//    }
    @PostMapping
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok(userService.createNewUser(userDTO));
    }
    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }

    @PatchMapping("/{username}")
    public ResponseEntity<?> editUserByUsername(@PathVariable("username") String username, @RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        return ResponseEntity.status(200).body(userService.editUserByUsername(username, userDTO));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.status(200).body("Delete Success");
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserChangePasswordDTO userChangePasswordDTO, HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        User userUpdate = userService.changePassword(userChangePasswordDTO, httpServletRequest);
        return ResponseEntity.status(201).body(userService.update(userUpdate));
    }

}
