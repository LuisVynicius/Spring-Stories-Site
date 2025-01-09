package com.mevy.stories.resources.exceptions;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mevy.stories.services.exceptions.ResourceNotFoundException;
import com.mevy.stories.services.exceptions.ResourceSaveException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(
        Exception exception,
        WebRequest webRequest
    ) {
        final int status = 500;
        ErrorResponse errorResponse = buildErrorResponse(exception, status);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(
        ResourceNotFoundException exception,
        WebRequest webRequest
    ) {
        final int status = 404;
        ErrorResponse errorResponse = buildErrorResponse(exception, status, exception.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(ResourceSaveException.class)
    public ResponseEntity<ErrorResponse> resourcesaveErrorHandler(
        ResourceSaveException exception,
        WebRequest webRequest
    ) {
        final int status = 409;
        ErrorResponse errorResponse = buildErrorResponse(exception, status, exception.getMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(Exception exception, int status) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .message("Um erro não tratado foi disparado.")
                                                    .timestamp(Instant.now())
                                                    .status(status)
                                                    .build();

        return errorResponse;
    }

    private ErrorResponse buildErrorResponse(Exception exception, int status, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .message(message)
                                                    .timestamp(Instant.now())
                                                    .status(status)
                                                    .build();

        return errorResponse;
    }

}
