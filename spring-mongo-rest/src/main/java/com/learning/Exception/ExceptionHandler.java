package com.learning.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> userNotFoundExceptionHandler() {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
