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
        Assertions.assertTrue(CustomerNameValidator.isValidName("John Doe"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("Alice Smith"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("Robert Brown"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("Emily Johnson"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("David Lee"));
        Assertions.assertTrue(CustomerNameValidator.isValidName("Samantha Whitehouse"));
    }

    @Test
    void shouldAbleToReturnFalseIfCustomerNamePassesIsInValid() {
        // arrange, act && assert
        assertFalse(CustomerNameValidator.isValidName(""));
        assertFalse(CustomerNameValidator.isValidName(null));
        assertFalse(CustomerNameValidator.isValidName("BA"));
        assertFalse(CustomerNameValidator.isValidName("   Ba  A"));
        assertFalse(CustomerNameValidator.isValidName("Ba          "));
        assertFalse(CustomerNameValidator.isValidName("Baburao"));
        assertFalse(CustomerNameValidator.isValidName("Baburao A"));
        assertFalse(CustomerNameValidator.isValidName("@Baburao Ap"));
        assertFalse(CustomerNameValidator.isValidName("Babur$ Apte"));
        assertFalse(CustomerNameValidator.isValidName("Baburao Apte%"));
        assertFalse(CustomerNameValidator.isValidName("$%^* Apte%"));
        assertFalse(CustomerNameValidator.isValidName("4789Apte%"));
        assertFalse(CustomerNameValidator.isValidName("9786789089"));
    }

}