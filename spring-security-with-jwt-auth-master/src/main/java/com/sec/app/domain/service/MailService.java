package com.sec.app.domain.service;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendEmail(String to, String subject, String body) throws MessagingException;
}
