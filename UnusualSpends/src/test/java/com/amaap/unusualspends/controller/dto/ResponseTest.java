package com.amaap.unusualspends.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {
    @Test
    public void shouldBeAbleToReturnResponseMessage() {
        // arrange
        String message = "sample message";
        Response response = new Response(HttpStatus.OK, message);

        // act && assert
        assertEquals(message, response.getMessage());
    }

    @Test
    public void shouldBeAbleTOTestEqualityAndUnEqualityForTwoInstancesOfResponse() {
        // arrange
        Response response1 = new Response(HttpStatus.OK, "sample message");
        Response response2 = new Response(HttpStatus.OK, "sample message");
        Response response3 = new Response(HttpStatus.BAD_REQUEST, "sample message");
        Response response4 = new Response(HttpStatus.OK, "other sample message");

        // act && assert
        assertAll(
                () -> assertTrue(response1.equals(response1)),
                () -> assertTrue(response1.equals(response2)),
                () -> assertTrue(response2.equals(response1)),
                () -> assertFalse(response1.equals(null)),
                () -> assertFalse(response1.equals("Response")),
                () -> assertFalse(response1.equals(response3)),
                () -> assertFalse(response3.equals(response1)),
                () -> assertFalse(response1.equals(response4)),
                () -> assertFalse(response4.equals(response1))
        );
    }

    @Test
    public void shouldBeAbleToReturnHashCode() {
        // arrange
        Response response1 = new Response(HttpStatus.OK, "Test message");
        Response response2 = new Response(HttpStatus.OK, "Test message");

        // act && assert
        assertEquals(response1.hashCode(), response2.hashCode());
    }
}