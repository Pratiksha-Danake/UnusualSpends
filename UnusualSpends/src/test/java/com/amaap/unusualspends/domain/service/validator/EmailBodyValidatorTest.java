package com.amaap.unusualspends.domain.service.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmailBodyValidatorTest {
    @Test
    void shouldBeAbleToCreateInstanceOfEmailBodyValidator() {
        // arrange && act
        EmailBodyValidator emailBodyValidator = new EmailBodyValidator();

        // assert
        assertNotNull(emailBodyValidator);
    }
}