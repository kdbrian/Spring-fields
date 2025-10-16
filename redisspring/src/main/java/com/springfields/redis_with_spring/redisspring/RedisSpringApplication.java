package com.springfields.redis_with_spring.redisspring;

import com.springfields.redis_with_spring.redisspring.domain.model.Todo;
import com.springfields.redis_with_spring.redisspring.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RedisSpringApplication {

    private final TodoService todoService;

    public static void main(String[] args) {
        SpringApplication.run(RedisSpringApplication.class, args);
    }

}
