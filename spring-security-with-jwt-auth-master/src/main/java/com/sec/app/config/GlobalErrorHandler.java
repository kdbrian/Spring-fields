package com.sec.app.config;

import com.sec.app.domain.dto.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<String>> handleExpiredJwtException(
            HttpServletRequest request,
            ExpiredJwtException e
    ) {
        if (request.getAttribute("expired") != null) {
            log.info("error {}", e.getMessage());
            ApiResponse<String> apiResponse = new ApiResponse<String>(
                    e.getLocalizedMessage(),
                    "failed",
                    HttpStatus.BAD_REQUEST.value()
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getCode()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
