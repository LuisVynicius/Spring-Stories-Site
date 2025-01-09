package com.mevy.stories.services.exceptions;

public class ResourceSaveException extends RuntimeException {
    
    public ResourceSaveException(Class<?> object, String message) {
        super("Erro ao salver: " + object.getSimpleName() + ". " + message);
    }

}
