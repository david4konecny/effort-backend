package com.example.effort.util.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class DataNotValidException extends RuntimeException {
    private final List<ObjectError> errors;
    private final StringBuilder sb = new StringBuilder();

    public DataNotValidException(List<ObjectError> errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        for (ObjectError err : errors) {
            sb.append(err.getDefaultMessage());
            sb.append("; ");
        }
        return sb.toString();
    }
}
