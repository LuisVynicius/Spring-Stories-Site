package com.mevy.metales_backend.exceptions.errors;

public class ValidationException extends RuntimeException {
    
    public ValidationException(String message) {
        super(message);
    }

}
