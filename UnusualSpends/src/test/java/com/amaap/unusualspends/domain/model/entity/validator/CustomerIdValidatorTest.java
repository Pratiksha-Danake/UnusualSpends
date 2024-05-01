package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerIdValidatorTest {
    @Test
    void shouldBeAbleToReturnTrueIfCustomerIdIsValid() {
        // arrange
        int customerId = 1;

        // act && assert
        Assertions.assertTrue(CustomerIdValidator.isValidId(customerId));
    }

    @Test
    void shouldBeAbleToReturnFalseIfCustomerIdIsInvalid() {
        // arrange
        int customerId = -1;

        // act && assert
        Assertions.assertFalse(CustomerIdValidator.isValidId(customerId));
        Assertions.assertFalse(CustomerIdValidator.isValidId(0));
    }
}