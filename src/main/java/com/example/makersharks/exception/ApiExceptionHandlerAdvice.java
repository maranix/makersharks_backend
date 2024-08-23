package com.example.makersharks.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.makersharks.common.Response;
import com.example.makersharks.common.ResponseErrorInfo;
import com.example.makersharks.enums.ResponseErrorCode;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ApiExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public Response responseHandler(Exception e) {
        return Response.error(HttpStatus.INTERNAL_SERVER_ERROR,
                ResponseErrorInfo.fromCode(ResponseErrorCode.VALIDATION_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleValidationException(MethodArgumentNotValidException e) {
        List<String> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream().map(err -> err.getField())
                .collect(Collectors.toList());

        ResponseErrorInfo errorInfo = ResponseErrorInfo.fromCode(ResponseErrorCode.VALIDATION_ERROR)
                .withFieldErrors(fieldErrors);

        return Response.error(HttpStatus.BAD_REQUEST, errorInfo);
    }
}
