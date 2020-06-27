package com.example.effort.user;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("Incorrect password");
    }
}
