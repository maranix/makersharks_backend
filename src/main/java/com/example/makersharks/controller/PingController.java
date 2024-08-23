package com.example.makersharks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.makersharks.common.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api")
public class PingController {

    private static final String pong = "Pong!";

    @Operation(description = "Ping the server", responses = {
            @ApiResponse(responseCode = "200", description = "Successful ping response with status and data, error field is omitted.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = {
                            @ExampleObject(value = """
                                    {
                                        "status": 200,
                                        "success": true,
                                        "data": "Pong!"
                                    }
                                    """)
                    })
            })
    })
    @GetMapping("/ping")
    public Response<String> getPing() {
        return Response.success(HttpStatus.OK, pong);
    }
}
