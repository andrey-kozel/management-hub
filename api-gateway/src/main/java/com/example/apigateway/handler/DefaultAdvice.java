package com.example.apigateway.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handeException() {
        return ResponseEntity.badRequest().build();
    }
}
