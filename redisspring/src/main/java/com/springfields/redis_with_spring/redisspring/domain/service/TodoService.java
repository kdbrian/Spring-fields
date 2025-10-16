package com.springfields.redis_with_spring.redisspring.domain.service;

import com.springfields.redis_with_spring.redisspring.domain.model.Todo;
import com.springfields.redis_with_spring.redisspring.domain.model.Todo.TodoBuilder;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> allTodo();
    Todo addTodo(TodoBuilder builder);
    Todo updateTodo(String id,TodoBuilder builder);
    Boolean deleteTodo(String id);
    Optional<Todo> todoById(String id);
}
