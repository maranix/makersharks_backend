package com.example.makersharks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getPing() {
        ResponseEntity<String> responseEntity = template.getForEntity("/api/ping", String.class);

        // Check that the status code is 200 OK
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check that the body returned correct data
        assertThat(responseEntity.getBody()).isEqualTo("Pong!");
    }
}
