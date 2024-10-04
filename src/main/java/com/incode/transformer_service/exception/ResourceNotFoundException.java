package com.incode.transformer_service.exception;

/**
 * Exception thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor with message.
     *
     * @param message The message.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     *
     * @param message The message.
     * @param cause   The cause.
     */
    public ResourceNotFoundException(
            final String message, final Throwable cause
    ) {
        super(message, cause);
    }
}
