package com.example.travel.services;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

public interface IMailService {
    void send(String subject, String content, String to, Boolean isHtmlFormat) throws MessagingException;

    void send(String subject, String content, String to, MultipartFile[] files, Boolean isHtmlFormat) throws MessagingException;
}
