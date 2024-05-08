package com.amaap.unusualspends.domain.model.entity.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerEmailIdValidatorTest {

    @Test
    void shouldAbleToCreateInstanceOfCustomerEmailIdValidator() {
        // arrange && act
        CustomerEmailIdValidator customerEmailIdValidator = new CustomerEmailIdValidator();
        // assert
        Assertions.assertNotNull(customerEmailIdValidator);
    }

    @Test
    void shouldBeAbleToReturnTrueForAllValidEmails() {
        // act && assert
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("danakepratiksha1020@gmail.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha.danake@example.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha_danake123@gmail.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("p.danake@email.co.uk"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha.d@example.net"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pdanake123@company.org"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha.danake@website.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha1234@email-provider.info"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha.danake@email-domain.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("p.danake@example-provider.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha-danake@gmail.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("pratiksha_danake@testmail.com"));
    }

    @Test
    void shouldBeAbleToReturnFalseForAllValidEmails() {
        // act && assert
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha*danake.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha*danake@example"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha5danake@example."));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha=danake@.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha*danake@gmail.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha$danake@gma il.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha.danake@gmail_com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha*danake#gmial.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha!danake$gmail com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha*danake%.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha/danake^.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha}danake=gma@il"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("pratiksha_danake@"));
    }
}