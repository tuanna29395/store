package com.anhtuan.store.service.impl;

import com.anhtuan.store.service.BaseService;
import com.anhtuan.store.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl extends BaseService implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    @Async
    public void sendEmailForgotPassword(String to, String subject, String resetPasswordUrl) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText("click this link for reset password: " + resetPasswordUrl);
        emailSender.send(msg);
    }
}
