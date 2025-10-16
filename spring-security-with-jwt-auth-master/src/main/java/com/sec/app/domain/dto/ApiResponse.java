package com.sec.app.domain.dto;

import lombok.*;

import java.util.function.Function;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private int code;
}
