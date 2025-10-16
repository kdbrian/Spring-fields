package com.springfields.redis_with_spring.redisspring.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Todo {
    private final String id= UUID.randomUUID().toString().split("-")[0];
    private String title;
    private String description;
    private Long updateAt = System.currentTimeMillis();

    private Todo(){}
    private Todo(String title, String description){
        this.title=title;
        this.description = description;
    }

    private Todo(String title, String description, Long updateAt){
        this.title=title;
        this.description = description;
        this.updateAt = updateAt;
    }

}
