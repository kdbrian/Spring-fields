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
        Schema schema = new Schema()
                .addTextField("title", 2.0)
                .addTextField("content", 3.0);

        //TODO: use indexing to retrieve all documents wit prefix todo# in key
        //now -> retrieve all keys {contains = todo#}
        ScanResult<String> result = jedis.scan(ScanParams.SCAN_POINTER_START, new ScanParams().match("todo#*"));
        List<Map<String, String>> ret = new ArrayList<>();
        List<Todo> todos = result.getResult().stream().map(jedis::hgetAll).map(Todo::fromStringMap).toList();
        return todos;
    }

    @Override
    public Map<String, String> todoById(String id) {
        String todoId = String.format("todo#%s", id);
        return jedis.hgetAll(todoId);
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
        if (tempTodo.isEmpty())
            return null;

        //update fields
        Todo built = builder.build();
        //replace existing updateAt=now()
        Todo updated = Todo.builder()
                .id(id)
                .title(built.getTitle() != null && !built.getTitle().isEmpty() ? built.getTitle() : tempTodo.get("title"))
                .description(built.getDescription() != null && !built.getDescription().isEmpty() ? built.getDescription() : tempTodo.get("description"))
                .updateAt(System.currentTimeMillis())
                .build();
        log.info("builder {}", updated);
        return addTodo(updated);
    }

    @Override
    public Boolean deleteTodo(String id) {
        if (todoById(id).isEmpty())
            return false;
        return jedis.del(String.format("todo#%s", id)) != -1;
    }

}