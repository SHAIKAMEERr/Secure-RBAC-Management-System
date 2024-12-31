package com.example.userauth.exception;

import java.time.LocalDateTime;

public class UserNotFoundException extends RuntimeException {

    private String errorCode;
    private LocalDateTime timestamp;

    // Constructor to initialize message and error code
    public UserNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    // Constructor to initialize message, error code, and cause
    public UserNotFoundException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    // Getters for errorCode and timestamp
    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
