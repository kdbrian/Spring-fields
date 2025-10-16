package com.springfields.redis_with_spring.redisspring.domain.repo;

import com.springfields.redis_with_spring.redisspring.domain.model.Todo;
import com.springfields.redis_with_spring.redisspring.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.json.Path;
import redis.clients.jedis.json.Path2;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RedisTodoServiceImpl implements TodoService {

    private final UnifiedJedis jeddis;


    @Override
    public List<Todo> allTodo() {
        return (List<Todo>) jeddis.jsonGet("todos");
    }

    @Override
    public Todo addTodo(Todo.TodoBuilder builder) {
        Todo todo = builder.build();
        jeddis.jsonSet(todo.getId(), todo);
        return todo;
    }

    @Override
    public Todo updateTodo(String id, Todo.TodoBuilder builder) {
        if (jeddis.jsonGet(id) == null)
            return null;
        Todo todo1 = builder
                .updateAt(System.currentTimeMillis())
                .build();
        jeddis.jsonMerge(id, Path2.ROOT_PATH, todo1);
        return todo1;
    }

    @Override
    public Boolean deleteTodo(String id) {
        return jeddis.jsonDel(id) != -1;
    }

    @Override
    public Optional<Todo> todoById(String id) {
        Object object = jeddis.jsonGet(id);
        if (object != null)
            return Optional.of((Todo) object);
        return Optional.empty();
    }
}

