package com.example.makersharks.exception;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.makersharks.common.Response;
import com.example.makersharks.common.ResponseErrorInfo;
import com.example.makersharks.enums.ResponseErrorCode;
import com.example.makersharks.utils.Util;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ApiExceptionHandlerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public Response responseHandler(RuntimeException e) {
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR,
                ResponseErrorInfo.fromCode(ResponseErrorCode.SERVER_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new HashMap<>();

        e.getBindingResult()
                .getFieldErrors().forEach(err -> {
                    fieldErrors.put(Util.convertCamelToSnakeCase(err.getField()), err.getDefaultMessage());
                });

        ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.VALIDATION_ERROR)
                .withFieldErrors(fieldErrors);

        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, errorInfo);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        // Check for specific JSON parsing exceptions
        Throwable cause = e.getCause();
        if (cause instanceof JsonParseException) {
            return handleJsonParseException((JsonParseException) cause);
        } else if (cause instanceof JsonMappingException) {
            return handleJsonMappingException((JsonMappingException) cause);
        }

        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY,
                ResponseErrorInfo.fromCode(ResponseErrorCode.VALIDATION_ERROR)
                        .withMessage("Malformed JSON request"));
    }

    private Response handleJsonParseException(JsonParseException e) {
        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY,
                ResponseErrorInfo.fromCode(ResponseErrorCode.VALIDATION_ERROR)
                        .withMessage("Invalid JSON format"));
    }

    private Response handleJsonMappingException(JsonMappingException e) {
        Map<String, String> fieldErrors = new HashMap<>();

        e.getPath().forEach(reference -> {
            String fieldName = reference.getFieldName();
            if (fieldName != null) {
                fieldErrors.put(Util.convertCamelToSnakeCase(fieldName), "Invalid value");
            }
        });

        ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.VALIDATION_ERROR)
                .withFieldErrors(fieldErrors)
                .withMessage("Error mapping JSON to object");

        return Response.error(HttpStatus.UNPROCESSABLE_ENTITY, errorInfo);
    }
}
