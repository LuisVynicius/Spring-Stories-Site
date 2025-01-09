package com.mevy.stories.resources.exceptions;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mevy.stories.services.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(
        Exception exception,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = buildErrorResponse(exception);
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(
        ResourceNotFoundException exception,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = buildErrorResponse(exception);
        return ResponseEntity.status(404).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .message(exception.getMessage())
                                                    .timestamp(Instant.now())
                                                    .status(404)
                                                    .build();

        return errorResponse;
    }

}
