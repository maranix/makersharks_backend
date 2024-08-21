package com.example.makersharks.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(
                int status,
                boolean success,
                String error,
                T data) {

        public static <T> Response<T> success(HttpStatus status, T data) {
                return new Response<T>(status.value(), true, null, data);
        }

        public static <T> Response<T> error(HttpStatus status, String error, T data) {
                if (error == null) {
                        throw new IllegalArgumentException("error cannot be null.");
                }

                return new Response<T>(status.value(), false, error, data);
        }
}
