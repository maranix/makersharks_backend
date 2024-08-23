package com.example.makersharks.common;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response<T> {

    @Schema(description = "Http Status Code")
    private int status;

    @Schema(description = "Indicates whether the request was succesful")
    private boolean success;

    @Schema(description = "Error object representing the details of failure")
    private ResponseErrorInfo error;

    @Schema(description = "Data associated to the request")
    private T data;

    public Response() {
    }

    public Response(int status, boolean success, ResponseErrorInfo error, T data) {
        this.status = status;
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResponseErrorInfo getError() {
        return error;
    }

    public void setError(ResponseErrorInfo error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response<T> build() {
        return new Response<T>(status, success, error, data);
    }

    public Response<T> withStatus(HttpStatus status) {
        setStatus(status.value());
        return this;
    }

    public Response<T> withSuccess(boolean success) {
        setSuccess(success);
        return this;
    }

    public Response<T> withError(ResponseErrorInfo error) {
        setError(error);
        return this;
    }

    public Response<T> withData(T data) {
        setData(data);
        return this;
    }

    public static <T> Response<T> success(HttpStatus status, T data) {
        return new Response<T>()
                .withStatus(status)
                .withSuccess(true)
                .withData(data)
                .build();
    }

    public static <T> Response<T> error(HttpStatus status, ResponseErrorInfo errorInfo) {
        return new Response<T>()
                .withStatus(status)
                .withSuccess(false)
                .withError(errorInfo)
                .build();
    }
}