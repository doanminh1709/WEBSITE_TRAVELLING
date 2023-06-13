package com.example.travel.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("user")
@Getter
@Setter
public class UserInfoProperties {
    private String fullName;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;
    private String dateOfBirth;
    private String role;
}
