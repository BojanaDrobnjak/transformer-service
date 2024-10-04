package com.incode.transformer_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle any exception that is not explicitly handled.
     *
     * @param ex      The exception that was thrown.
     * @param request The web request.
     * @return A response entity with an error message and status code.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(
            final Exception ex, final WebRequest request
    ) {
        return new ResponseEntity<>(
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Handle a resource not found exception.
     *
     * @param ex      The exception that was thrown.
     * @param request The web request.
     * @return A response entity with an error message and status code.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(
            final ResourceNotFoundException ex, final WebRequest request
    ) {
        return new ResponseEntity<>(
                "Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND
        );
    }
}
