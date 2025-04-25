package com.mevy.metales_backend.exceptions.errors;

public class DatabaseIntegrityException extends RuntimeException{
 
    public DatabaseIntegrityException(String message) {
        super(message);
    }
    
}
