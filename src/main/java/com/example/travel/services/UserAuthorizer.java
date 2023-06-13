package com.example.travel.services;

import org.springframework.security.core.Authentication;

public interface UserAuthorizer {
    boolean isAdmin(Authentication authentication);

    boolean isUser(Authentication authentication);

}
