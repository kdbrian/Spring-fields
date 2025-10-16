package com.sec.app.domain.dto;

import com.sec.app.model.AppUser;
import lombok.Builder;

@Builder
public record AuthResponse(
        String token,
        AppUser user
) {
}
