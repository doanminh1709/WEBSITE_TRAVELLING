package com.example.travel.services.imp;

import com.example.travel.services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(String subject, String content, String to, Boolean isHtmlFormat) throws MessagingException {
        if (isHtmlFormat == null) {
            isHtmlFormat = false;
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setSubject(subject);
        helper.setText(content, isHtmlFormat);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }

    @Override
    public void send(String subject, String content, String to, MultipartFile[] files, Boolean isHtmlFormat) throws MessagingException {
        if (isHtmlFormat == null) {
            isHtmlFormat = false;
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        helper.setSubject(subject);
        helper.setText(content, isHtmlFormat);
        helper.setTo(to);

        if (files != null && files.length > 0) {
            for (MultipartFile multipartFile : files) {
                helper.addAttachment(multipartFile.getName(), multipartFile);
            }
        }

        mailSender.send(mimeMessage);
    }
}
