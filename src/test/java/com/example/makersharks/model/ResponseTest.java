package com.example.makersharks.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ResponseTest {

    @Test
    void testSuccessResponse() {
        HttpStatus status = HttpStatus.OK;
        String data = "Test data";

        Response<String> response = Response.success(status, data);

        assertEquals(status.value(), response.status());
        assertTrue(response.success());
        assertNull(response.error());
        assertEquals(data, response.data());
    }

    @Test
    void testErrorResponse() {
        // Arrange
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = "An error occurred";
        String data = null;

        Response<String> response = Response.error(status, error, data);

        assertEquals(status.value(), response.status());
        assertFalse(response.success());
        assertEquals(error, response.error());
        assertNull(response.data());
    }
}
