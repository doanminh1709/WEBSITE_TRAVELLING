package com.example.travel.controllers;

import com.example.travel.dtos.UserDTO;
import com.example.travel.models.AuthenticationRequest;
import com.example.travel.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "*")
@CrossOrigin("http://localhost:3000")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }

    @DeleteMapping("/resetPassword/{username}")
    public ResponseEntity<?> resetPassword(@PathVariable("username") String username, HttpServletRequest request) {
        authService.resetPassword(username, request);
        return ResponseEntity.status(201).body("We have sent a new password to your email address, please check your inbox");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.ok(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }
        return ResponseEntity.ok(authService.signup(userDTO));
    }


}
