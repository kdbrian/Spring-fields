package com.springfields.redis_with_spring.redisspring.api.rest;

import com.google.gson.JsonObject;
import com.springfields.redis_with_spring.redisspring.domain.model.Todo;
import com.springfields.redis_with_spring.redisspring.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
@Slf4j
public class TodoApi {

    private final TodoService todoService;

    @GetMapping
    ResponseEntity<List<Todo>> loadAllTodos() {
        List<Todo> todos = todoService.allTodo();
        return ResponseEntity.ok(todos);
    }


    @PostMapping("/")
    ResponseEntity<Map<String, Object>> createTodo(
            @RequestBody Todo todo
    ) {

        Todo addedTodo = todoService.addTodo(todo);

        var res = new HashMap<String, Object>();
        res.put("success", true);
        res.put("data", addedTodo);

        return new ResponseEntity<>(res, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/{id}/")
    ResponseEntity<Map<String, String>> todoById(@PathVariable String id) {
        Map<String, String> todoById = todoService.todoById(id);
//        todoById.putIfAbsent("status", "success");
        return ResponseEntity.ok(todoById);
    }

    @PutMapping("update/{id}/")
    ResponseEntity<Map<String, Object>> updateTodo(
            @PathVariable String id,
            @RequestBody Todo todo

    ) {
        return ResponseEntity.ok(todoService.updateTodo(id, Todo.builder().title(todo.getTitle()).description(todo.getDescription())).toUntypedMap());
    }

    @DeleteMapping("delete/{id}/")
    ResponseEntity<Map<String, Object>> updateTodo(@PathVariable String id) {
        return ResponseEntity.ok(Collections.singletonMap("success", todoService.deleteTodo(id)));
    }

}
