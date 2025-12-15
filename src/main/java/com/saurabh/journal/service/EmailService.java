package com.saurabh.journal.service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendEmail(String to, String subject, String text) {
       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setSubject(subject);
           message.setFrom("saurabh.kori.dev@gmail.com");
           message.setText(text);
           mailSender.send(message);
       } catch (Exception e) {
           log.error("Exception by SendMail",e.getMessage());
       }
    }
 }
