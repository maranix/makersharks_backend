package com.example.makersharks.enums;

/**
 * This enum represents various response error codes along with their
 * descriptions and user-friendly messages.
 */
public enum ResponseErrorCode {

    /**
     * E0500: An unexpected server error occurred.
     */
    SERVER_ERROR("E0500", "Server Error", "An unexpected server error occurred."),

    /**
     * E0001: Failed to create the requested resource.
     */
    CREATION_ERROR("E0001", "Creation Error", "Failed to create the requested resource."),

    /**
     * E0002: The requested resource could not be found.
     */
    NOT_FOUND("E0002", "Not Found", "The requested resource could not be found."),

    /**
     * E0003: Validation failed for one or more fields.
     */
    VALIDATION_ERROR("E0003", "Validation Error", "Validation failed for one or more fields."),

    /**
     * E0004: The resource already exists.
     */
    ALREADY_EXISTS("E0004", "Already Exists", "The resource already exists.");

    private final String code;
    private final String description;
    private final String message;

    ResponseErrorCode(String code, String description, String message) {
        this.code = code;
        this.description = description;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }
}
