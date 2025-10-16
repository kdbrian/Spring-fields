package com.springfields.redis_with_spring.redisspring;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RedisSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSpringApplication.class, args);
    }

}
