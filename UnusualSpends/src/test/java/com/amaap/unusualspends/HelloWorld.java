package com.amaap.unusualspends;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorld {
    @Test
    void shouldAddTwoNumbers(){
        // arrange
        int a = 2;
        int b = 2;
        int expectedSum = 4;
        // act
        int c = Addition.add(a,b);
        // assert
        assertEquals(expectedSum,c);
    }
}
