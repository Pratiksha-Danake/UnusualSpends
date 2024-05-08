package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CustomerNameValidatorTest {

    @Test
    void shouldBeAbleToCreateInstanceOfCustomerNameValidator() {
        // arrange && act
        CustomerNameValidator customerNameValidator = new CustomerNameValidator();
        // act
        Assertions.assertNotNull(customerNameValidator);
    }

    @Test
    void shouldAbleToReturnTrueIfCustomerNamePassedIsValid() {
        // arrange, act && assert
        Assertions.assertTrue(CustomerNameValidator.isValidName("Pratiksha dana"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("pratiksha Danake"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("Pratiksha Danake"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("pratiksha Dan"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("Pratiksha Danakeeeeeeee"));
    }

    @Test
    void shouldAbleToReturnFalseIfCustomerNamePassesIsInValid() {
        // arrange, act && assert
        assertFalse(CustomerNameValidator.isValidName(" "));
        assertFalse(CustomerNameValidator.isValidName("PD    "));
        assertFalse(CustomerNameValidator.isValidName("   Pra  DA"));
        assertFalse(CustomerNameValidator.isValidName("         PD"));
        assertFalse(CustomerNameValidator.isValidName("Pratiksha"));
        assertFalse(CustomerNameValidator.isValidName("Pratiksha D"));
        assertFalse(CustomerNameValidator.isValidName("@Pratiksha Da"));
        assertFalse(CustomerNameValidator.isValidName("Pratiksha$ Danake"));
        assertFalse(CustomerNameValidator.isValidName("Pratiksha@ danake$$$$"));
        assertFalse(CustomerNameValidator.isValidName("@%^^%CFWSDF $@Y$Y"));
        assertFalse(CustomerNameValidator.isValidName(null));
        assertFalse(CustomerNameValidator.isValidName("4789Apte%"));
        assertFalse(CustomerNameValidator.isValidName("35465465465"));
    }
}