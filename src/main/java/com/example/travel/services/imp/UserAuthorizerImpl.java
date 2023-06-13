package com.example.travel.services.imp;

import com.example.travel.constants.RoleConstant;
import com.example.travel.repositories.UserRepository;
import com.example.travel.services.IUserService;
import com.example.travel.services.UserAuthorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service("userAuthorizer")
public class UserAuthorizerImpl implements UserAuthorizer {

    @Override
    public boolean isAdmin(Authentication authentication) {
        return Arrays.toString(authentication.getAuthorities().toArray()).contains(RoleConstant.ROLE_ADMIN);
    }

    @Override
    public boolean isUser(Authentication authentication) {
        return Arrays.toString(authentication.getAuthorities().toArray()).contains(RoleConstant.ROLE_USER);
    }

}

