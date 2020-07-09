package com.example.effort.util;

public class ErrorInfo {
    private final long timestamp = System.currentTimeMillis();
    private final String path;
    private final String message;

    public ErrorInfo(String path, String message) {
        this.path = path;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
