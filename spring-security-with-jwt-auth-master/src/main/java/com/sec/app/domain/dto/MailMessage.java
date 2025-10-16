package com.sec.app.domain.dto;

import lombok.Builder;

@Builder
public record MailMessage(
        String to,
        String subject,
        String message
) {
}
