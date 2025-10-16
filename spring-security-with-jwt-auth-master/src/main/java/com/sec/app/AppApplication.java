package com.sec.app;

import com.sec.app.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AppApplication {

    private final UserRepo userRepo;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }
    @Bean
    public UserDetailsService userDetailsService() {
        //always make sure it throws(avoid cyclic dependencies)
        return username -> userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Missing user " + username));
    }


}
