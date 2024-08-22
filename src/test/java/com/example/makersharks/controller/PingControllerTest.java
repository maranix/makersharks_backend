package com.example.makersharks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.makersharks.model.Response;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SuppressWarnings({ "rawtypes", "null" })
public class PingControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getPing() {
        ResponseEntity<Response> responseEntity = template.getForEntity("/api/ping", Response.class);

        // Check that the status code is 200 OK
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Check that the response body matches the expected value
        Response responseBody = responseEntity.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.status()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseBody.success()).isTrue();
        assertThat(responseBody.error()).isNull();
        assertThat(responseBody.data()).isEqualTo("Pong!");
    }
}
