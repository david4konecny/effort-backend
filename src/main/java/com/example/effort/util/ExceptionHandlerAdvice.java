package com.example.effort.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handle(HttpServletRequest req, DataNotValidException e) {
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), e.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

}
