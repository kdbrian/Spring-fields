package com.springfields.redis_with_spring.redisspring.domain.api.rest;

import com.springfields.redis_with_spring.redisspring.domain.model.Todo;
import com.springfields.redis_with_spring.redisspring.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoApi {

    private final TodoService todoService;


    ResponseEntity<List<Todo>> loadAllTodos(){
        return ResponseEntity.of(todoService.)
    }
}
