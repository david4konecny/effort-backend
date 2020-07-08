package com.example.effort.util;

public class ErrorInfo {
    private final long timestamp = System.currentTimeMillis();
    private final String uri;
    private final String message;

    public ErrorInfo(String uri, String message) {
        this.uri = uri;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }

    public String getMessage() {
        return message;
    }
}
