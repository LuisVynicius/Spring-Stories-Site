package com.mevy.stories.resources.exceptions;

import java.io.IOException;
import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.mevy.stories.services.exceptions.ResourceNotFoundException;
import com.mevy.stories.services.exceptions.ResourceSaveException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler implements AuthenticationFailureHandler {
    
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

    @Override
    public void onAuthenticationFailure(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException exception
    ) throws IOException, ServletException {
        final int status = 401;
        response.setStatus(status);
        response.setContentType("application/json");
        ErrorResponse errorResponse = buildErrorResponse(exception, status, exception.getMessage());
        response.getWriter().append(errorResponse.toJson());
    }

    private ErrorResponse buildErrorResponse(Exception exception, int status) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .message(exception.getMessage())
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
