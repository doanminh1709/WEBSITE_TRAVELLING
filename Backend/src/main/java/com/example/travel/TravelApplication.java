package com.example.travel;

import com.example.travel.configs.MailInfoProperties;
import com.example.travel.configs.StorageProperties;
import com.example.travel.configs.UserInfoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, UserInfoProperties.class, MailInfoProperties.class})
@EnableWebMvc
@EnableScheduling
public class TravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }

}
