package com.sec.app.domain.dto;

import com.sec.app.model.AppUser;
import lombok.Builder;

@Builder
public record AppUserDto(
        String username,
        String email,
        String password

) {

    public AppUser toAppUser(){
        return new AppUser(null, email, password, username);
    }
}
