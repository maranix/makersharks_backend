package com.example.makersharks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.makersharks.model.Response;

@RestController
@RequestMapping("/api")
public class PingController {

    private static final String pong = "Pong!";

    @GetMapping("/ping")
    public Response<String> ping() {
        return Response.success(HttpStatus.OK, pong);
    }
}
