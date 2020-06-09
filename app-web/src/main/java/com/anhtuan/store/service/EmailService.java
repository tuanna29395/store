package com.anhtuan.store.service;

public interface EmailService {
    void sendEmailForgotPassword(String to, String subject, String resetPasswordUrl);
}
