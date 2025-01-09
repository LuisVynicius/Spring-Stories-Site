package com.mevy.stories.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> object) {
        super(object.getSimpleName() + " não encontrado.");
    }
    
}
