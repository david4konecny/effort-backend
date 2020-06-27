package com.example.effort.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserAdvice {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handle(UsernameAlreadyExistsException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handle(IncorrectPasswordException e) {
        Map<String, String> body = new HashMap<>();
        body.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handle(UserNotValidException e) {
        Map<String, String> body = new HashMap<>();
        for (ObjectError err : e.getErrors()) {
            body.put(((FieldError) err).getField(), err.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(body);
    }

}
