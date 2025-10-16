package com.sec.app.repo.impl;

import com.sec.app.domain.dto.AppUserDto;
import com.sec.app.domain.dto.AuthResponse;
import com.sec.app.domain.service.AuthService;
import com.sec.app.model.AppUser;
import com.sec.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser register(AppUserDto dto) {
        AppUser appUser = AppUser.builder()
                .email(dto.email())
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        AppUser save = userRepo.save(appUser);
        save.setPassword(null);
        return save;
    }

    @Override
    public AuthResponse login(AppUserDto dto) {
        return null;
    }

    @Override
    public AppUser getUserByUsername(String username) {
        return userRepo.findByEmail(username).orElseThrow();
    }
}
