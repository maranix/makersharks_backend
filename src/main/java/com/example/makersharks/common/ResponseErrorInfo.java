package com.example.makersharks.common;

import java.util.Map;

import com.example.makersharks.enums.ResponseErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseErrorInfo {

    @Schema(description = "A unique error code representing the specific error type.", example = "ERR_001")
    private String code;

    @Schema(description = "A brief description of the error to provide context or additional details.", example = "Invalid request parameters")
    private String description;

    @Schema(description = "A user-friendly message that can be displayed to the end-user explaining the error.", example = "The provided parameters are not valid.")
    private String message;

    @Schema(description = "List of fields that caused validation errors.")
    private Map<String, String> fieldErrors;

    public ResponseErrorInfo(String code, String description, String message, Map<String, String> fieldErrors) {
        this.code = code;
        this.description = description;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public ResponseErrorInfo() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ResponseErrorInfo withCode(String code) {
        setCode(code);
        return this;
    }

    public ResponseErrorInfo withDescription(String description) {
        setDescription(description);
        return this;
    }

    public ResponseErrorInfo withMessage(String message) {
        setMessage(message);
        return this;

    }

    public ResponseErrorInfo withFieldErrors(Map<String, String> fields) {
        setFieldErrors(fields);
        return this;
    }

    public static ResponseErrorInfo fromCode(ResponseErrorCode code) {
        return new ResponseErrorInfo()
                .withCode(code.getCode())
                .withDescription(code.getDescription())
                .withMessage(code.getMessage());
    }
}
