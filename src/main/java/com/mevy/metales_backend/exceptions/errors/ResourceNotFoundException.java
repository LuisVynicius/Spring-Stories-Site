package com.mevy.metales_backend.exceptions.errors;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
