package com.amaap.unusualspends.domain.service.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailSubjectValidatorTest {
    @Test
    void shouldBeAbleToCreateInstanceOfEmailSubjectValidator() {
        // arrange && act
        EmailSubjectValidator emailSubjectValidator = new EmailSubjectValidator();

        // assert
        assertNotNull(emailSubjectValidator);
    }

    @Test
    void shouldBeAbleToReturnTrueIfEmailSubjectIsValid() {
        // arrange
        String subject = "sampple subject";

        // act
        boolean isValidEmailSubject = EmailSubjectValidator.isValidEmailSubject(subject);

        // assert
        assertTrue(isValidEmailSubject);
    }
}