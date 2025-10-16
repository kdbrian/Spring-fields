package com.springfields.redis_with_spring.redisspring.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Todo {
    @Builder.Default
    String id = UUID.randomUUID().toString().split("-")[0];
    String title;
    String description;

    @Builder.Default
    Long updateAt = System.currentTimeMillis();

    private Todo() {
    }

    private Todo(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    private Todo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    private Todo(String title, String description, Long updateAt) {
        this.title = title;
        this.description = description;
        this.updateAt = updateAt;
    }


    public Map<String, Object> toUntypedMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("title", this.title);
        map.put("description", this.description);
        map.put("updateAt", this.updateAt);
        return map;
    }

    public static Todo fromStringMap(Map<String, String> stringMap) {
        Todo todo = Todo
                .builder()
                .title(stringMap.get("title"))
                .description(stringMap.get("description"))
                .updateAt(Long.valueOf(stringMap.get("updateAt")))
                .id(stringMap.get("id"))
                .build();
        return todo;
    }


}
