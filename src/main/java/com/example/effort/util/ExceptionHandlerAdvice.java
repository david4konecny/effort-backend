package com.example.effort.util;

import com.example.effort.util.exceptions.DataNotValidException;
import com.example.effort.util.exceptions.UpdateFailedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({DataNotValidException.class, UpdateFailedException.class, DateTimeParseException.class})
    public ResponseEntity<ErrorInfo> handle(HttpServletRequest req, Exception e) {
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), e.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handle(HttpServletRequest req, JsonProcessingException e) {
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), e.getOriginalMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

}
