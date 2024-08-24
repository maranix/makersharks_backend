package com.example.makersharks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import com.example.makersharks.common.Response;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingControllerTest {

    @Autowired
    private TestRestTemplate template;

    @SuppressWarnings("null")
    @Test
    public void getPing() {
        var responseEntity = template.getForEntity("/api/ping", Response.class);

        var expectedResponse = Response.success(HttpStatus.OK, "Pong!");

        // Check that the status code is 200 OK
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check that the body returned correct data
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(Response.class);
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(expectedResponse.getStatus());
        assertThat(responseEntity.getBody().getSuccess()).isEqualTo(expectedResponse.getSuccess());
        assertThat(responseEntity.getBody().getData()).isEqualTo(expectedResponse.getData());
    }
}
