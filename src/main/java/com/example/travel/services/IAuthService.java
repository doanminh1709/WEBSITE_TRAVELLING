package com.example.travel.services;

import com.example.travel.dtos.UserDTO;
import com.example.travel.models.AuthenticationRequest;
import com.example.travel.models.AuthenticationResponse;

import javax.servlet.http.HttpServletRequest;

public interface IAuthService {
    AuthenticationResponse login(AuthenticationRequest request);

    void resetPassword(String username, HttpServletRequest httpServletRequest);

    AuthenticationResponse signup(UserDTO userDTO);
}
