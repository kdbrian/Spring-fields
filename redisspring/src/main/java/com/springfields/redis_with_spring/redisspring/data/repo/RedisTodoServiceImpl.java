package com.springfields.redis_with_spring.redisspring.data.repo;

import com.springfields.redis_with_spring.redisspring.domain.model.Todo;
import com.springfields.redis_with_spring.redisspring.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;
import redis.clients.jedis.search.RediSearchUtil;
import redis.clients.jedis.search.Schema;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisTodoServiceImpl implements TodoService {

    private final UnifiedJedis jedis;

    @Override
    public List<Todo> allTodo() {
//        Schema schema = new Schema()
//                .addTextField("title", 2.0)
//                .addTextField("content", 3.0);

        //TODO: use indexing to retrieve all documents wit prefix todo# in key
        //now -> retrieve all keys {contains = todo#}
        ScanResult<String> result = jedis.scan(ScanParams.SCAN_POINTER_START, new ScanParams().match("todo#*"));
        return result.getResult().stream().map(jedis::hgetAll).map(Todo::fromStringMap).toList();
    }

    @Override
    public Todo todoById(String id) {
        String todoId = String.format("todo#%s", id);
        Map<String, String> hgetAll = jedis.hgetAll(todoId);

        if (hgetAll.isEmpty())
            return null;

        return Todo.fromStringMap(hgetAll);
    }

    @Override
    public Todo addTodo(Todo build) {
        build.setUpdateAt(System.currentTimeMillis());
        jedis.hset(String.format("todo#%s", build.getId()), RediSearchUtil.toStringMap(build.toUntypedMap()));
        return build;
    }

    @Override
    public Todo updateTodo(String id, Todo.TodoBuilder builder) {
        //retrieve todo
        var tempTodo = todoById(id);

        //update fields
        Todo built = builder.build();
        //replace existing updateAt=now()
        Todo updated = Todo.builder()
                .id(id)
                .title(built.getTitle() != null && !built.getTitle().isEmpty() ? built.getTitle() : tempTodo.getTitle())
                .description(built.getDescription() != null && !built.getDescription().isEmpty() ? built.getDescription() : tempTodo.getDescription())
                .updateAt(System.currentTimeMillis())
                .build();

        return addTodo(updated);
    }

    @Override
    public Boolean deleteTodo(String id) {
        if (todoById(id) == null)
            return false;
        return jedis.del(String.format("todo#%s", id)) != -1;
    }

}