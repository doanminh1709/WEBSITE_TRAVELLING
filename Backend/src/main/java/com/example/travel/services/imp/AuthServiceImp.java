package com.example.travel.services.imp;

import com.example.travel.daos.User;
import com.example.travel.dtos.UserDTO;
import com.example.travel.exceptions.BadRequestException;
import com.example.travel.exceptions.NotFoundException;
import com.example.travel.models.AuthenticationRequest;
import com.example.travel.models.AuthenticationResponse;
import com.example.travel.repositories.UserRepository;
import com.example.travel.services.IAuthService;
import com.example.travel.services.IMailService;
import com.example.travel.services.IUserService;
import com.example.travel.services.MyUserDetailsService;
import com.example.travel.utils.JwtUtil;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AuthServiceImp implements IAuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private IMailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect username or password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwt =jwtUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(request.getUsername());
        return new AuthenticationResponse(jwt, user.getId(), user.getUsername());
    }

    @Override
    public void resetPassword(String username, HttpServletRequest request) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new NotFoundException("Not found user with username");
        }

        String userAgent = request.getHeader("User-Agent");
        String time = new Date().toString();
        String newPassword = RandomString.make(10);

        Context context = new Context();
        context.setVariable("userAgent", userAgent);
        context.setVariable("time", time);
        context.setVariable("password", newPassword);
        String html = templateEngine.process("password-changed-email.html", context);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(() -> {
            try {
                mailService.send("Thay đổi mật khẩu", html, user.getEmail(), true);
                user.setPassword(passwordEncoder.encode(newPassword));
                userService.update(user);
            } catch (MessagingException ignored) {
            }
        });
    }

    @Override
    public AuthenticationResponse signup(UserDTO userDTO) {
        User oldUser = userRepository.findByUsername(userDTO.getUsername());
        if (oldUser != null) {
            throw new NotFoundException("Username has already exists");
        }

        User user = userService.createNewUser(userDTO);
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, user.getId(), user.getUsername());
    }
}
