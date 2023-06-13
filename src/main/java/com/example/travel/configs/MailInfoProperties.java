package com.example.travel.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("email")
@Getter
@Setter
public class MailInfoProperties {
    private String username;
    private String password;
}
