package com.example.effort.user;

import com.example.effort.util.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handle(HttpServletRequest req, UsernameAlreadyExistsException e) {
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorInfo);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handle(HttpServletRequest req, IncorrectPasswordException e) {
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURI(), e.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

}
