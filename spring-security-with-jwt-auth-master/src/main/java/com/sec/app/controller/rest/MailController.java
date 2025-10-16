package com.sec.app.controller.rest;

import com.sec.app.domain.dto.MailMessage;
import com.sec.app.domain.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class MailController {

    private final MailService mailService;

    @PostMapping("/mail")
    public ResponseEntity<String> sendMail(
            @RequestBody MailMessage message
    ) {
        try {
            mailService.sendEmail(message.to(), message.subject(), message.message());
            return ResponseEntity.ok("Message sent.🚀🎉");
        } catch (MessagingException e) {
            log.info("failed {}", e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
