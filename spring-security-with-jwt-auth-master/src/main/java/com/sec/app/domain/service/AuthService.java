package com.sec.app.domain.service;


import com.sec.app.domain.dto.AppUserDto;
import com.sec.app.domain.dto.AuthResponse;
import com.sec.app.model.AppUser;

public interface AuthService {
    AppUser register(AppUserDto dto);
    AuthResponse login(AppUserDto dto);
    AppUser getUserByUsername(String username);
}
