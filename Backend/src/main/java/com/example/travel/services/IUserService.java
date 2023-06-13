package com.example.travel.services;

import com.example.travel.daos.User;
import com.example.travel.dtos.UserChangePasswordDTO;
import com.example.travel.dtos.UserDTO;
import com.example.travel.dtos.UserUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User createNewUser(UserDTO userDTO);


    User editUserByUsername(String username, UserDTO userDTO);

    void deleteUserByUsername(String username);
    User update(UserUpdateDTO userUpdateDTO, User currentUser);

    User update(User user);

    User changePassword(UserChangePasswordDTO userChangePasswordDTO, HttpServletRequest httpServletRequest);

    User findByUsername(String username);
}
