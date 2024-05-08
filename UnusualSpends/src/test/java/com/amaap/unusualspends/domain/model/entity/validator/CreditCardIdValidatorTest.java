package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreditCardIdValidatorTest {
    @Test
    void shouldBeAbleToCreateInstanceOfCustomerIdValidator() {
        // arrange && act
        CreditCardIdValidator creditCardIdValidator = new CreditCardIdValidator();
        // assert
        assertNotNull(creditCardIdValidator);
    }
}