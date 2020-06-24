package com.example.effort.user;

import org.springframework.validation.ObjectError;

import java.util.List;

public class UserNotValidException extends RuntimeException {
    private final List<ObjectError> errors;

    public UserNotValidException(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }
}
