package com.example.travel.services.imp;

import com.example.travel.constants.RoleConstant;
import com.example.travel.daos.User;
import com.example.travel.dtos.UserChangePasswordDTO;
import com.example.travel.dtos.UserDTO;
import com.example.travel.exceptions.AppException;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.dtos.UserUpdateDTO;
import com.example.travel.repositories.UserRepository;
import com.example.travel.services.IUserService;
import com.example.travel.utils.Convert;
import com.sun.jdi.request.DuplicateRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new NotFoundException("Count not found with id: " + id);
        }
        return user.get();
    }

    @Override
    public User createNewUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userOld = userRepository.findByUsername(userDTO.getUsername());
        User userCheck = userRepository.findByEmail(userDTO.getEmail());
        if (userOld != null || userCheck != null) {
            throw new DuplicateRequestException("User has already exist");
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setRole(RoleConstant.ROLE_USER);
        return userRepository.save(user);
    }

    @Override
    public User update(UserUpdateDTO userUpdateDTO, User currentUser) {
        User updated = modelMapper.map(userUpdateDTO, User.class);
        modelMapper.map(updated, currentUser);
        if (userUpdateDTO.getPassword() != null) {
            currentUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
        return userRepository.save(currentUser);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User changePassword(UserChangePasswordDTO userChangePasswordDTO, HttpServletRequest httpServletRequest) {
        User requestedUser = (User) httpServletRequest.getAttribute("user");
        User user = userRepository.findByUsername(requestedUser.getUsername());
        if (!passwordEncoder.matches(userChangePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new AppException("oldPassword is incorrect");
        }
        user.setPassword(passwordEncoder.encode(userChangePasswordDTO.getNewPassword()));
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User editUserByUsername(String username, UserDTO userDTO) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isEmpty()){
            throw new NotFoundException("Count not found with username: " + username);
        }
        User newUser = Convert.fromUserDTOToUser(userDTO);
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new NotFoundException("Count not found with username: " + username);
        }
        userRepository.delete(user);
    }
}
