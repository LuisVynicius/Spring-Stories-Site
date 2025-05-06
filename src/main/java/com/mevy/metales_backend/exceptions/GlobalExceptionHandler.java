package com.mevy.metales_backend.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mevy.metales_backend.exceptions.errors.DatabaseIntegrityException;
import com.mevy.metales_backend.exceptions.errors.ResourceNotFoundException;
import com.mevy.metales_backend.exceptions.errors.ValidationException;
import com.mevy.metales_backend.exceptions.response.ErrorResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler implements AuthenticationFailureHandler {
    
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponse> uncaghtExceptionHandler(
    //     Exception exception
    // ) {
    //     ErrorResponse errorResponse = createErrorResponse("Um erro inesperado ocorreu");

    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    // }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExceptionHandler(
        ResourceNotFoundException exception
    ) {
        ErrorResponse errorResponse = createErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    @ExceptionHandler(DatabaseIntegrityException.class)
    public ResponseEntity<ErrorResponse> databaseIntegrityExceptionHandler(
        DatabaseIntegrityException exception
    ) {
        ErrorResponse errorResponse = createErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> validationExceptionHandler(
        ValidationException exception
    ) {
        ErrorResponse errorResponse = createErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> usernameNotFoundExceptionHandler(
        UsernameNotFoundException exception
    ) {
        ErrorResponse errorResponse = createErrorResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        Integer status = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(status);
        response.setContentType("Application/json");
        ErrorResponse errorResponse = createErrorResponse("Email não registrado");
        response.getWriter().append(errorResponse.toJson());
    }

    private ErrorResponse createErrorResponse(String text) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                    .text(text)
                                                    .build();

        return errorResponse;
    }

}
