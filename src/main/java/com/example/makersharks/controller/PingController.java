package com.example.makersharks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.makersharks.model.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class PingController {

    private static final String pong = "Pong!";

    @Operation(description = "Ping the server")
    @ApiResponse(responseCode = "200", description = "Successful ping response with status and message")
    @GetMapping("/ping")
    public Response<String> getPing() {
        return Response.success(HttpStatus.OK, pong);
    }
}
