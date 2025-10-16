package com.sec.app.controller.rest;

import com.sec.app.domain.dto.AppUserDto;
import com.sec.app.domain.dto.AuthResponse;
import com.sec.app.domain.service.AuthService;
import com.sec.app.domain.service.JwtService;
import com.sec.app.model.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {


    private final AuthService authService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public String testAuth() {
        var contextHolder = SecurityContextHolder.getContext();
        Authentication authentication = contextHolder.getAuthentication();
        return authentication.isAuthenticated() ? "You are authenticated " + authentication.getName() : "Missing auth";
    }

    @PostMapping("/register/")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AppUserDto appUserDto
    ) {
        AppUser registered = authService.register(appUserDto);
        return ResponseEntity.ok(AuthResponse.builder().user(registered).build());
    }

    @PostMapping("/login/")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AppUserDto dto
    ) {
        log.info("logging {}", dto);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );
        AppUser user = authService.getUserByUsername(dto.username());
        log.info("user {}", user);
        user.setPassword(null);

        return ResponseEntity.ok(
                AuthResponse.builder()
                        .user(user)
                        .token(jwtService.generateToken(null, user))
                        .build()
        );
    }

}
