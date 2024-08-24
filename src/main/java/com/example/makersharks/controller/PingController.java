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
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

// TODO: Handle ResponseEntity<Response> some other way, current solution for producing correct
//       http status codes in responses is very convulated and not really readable at all.
//
//       Don't really have enough time to figure out a solution so let's keep it that way.
//       Can be a good point for discussion in the interview.
@RestController
@RequestMapping("/api")
@Tag(name = "Ping")
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
    public ResponseEntity<Response<String>> getPing() {
        return ResponseEntity.ok(Response.success(HttpStatus.OK, pong));
    }
}
