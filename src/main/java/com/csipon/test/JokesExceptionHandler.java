package com.csipon.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class JokesExceptionHandler {

    @ExceptionHandler(value = {JokeLimitException.class})
    public ResponseEntity<String> handleLimitError(RuntimeException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(ex.getMessage());
    }
}
