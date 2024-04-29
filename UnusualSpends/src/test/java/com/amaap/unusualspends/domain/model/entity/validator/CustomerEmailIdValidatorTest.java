package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
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
    void shouldBeAbleToReturnTrueForAllValidEmails() throws InvalidCustomerEmailException {
        // act && assert
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("danakepratiksha1020@gmail.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("example@example.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("user123@test.org"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("john.doe@emailprovider.net"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("jane_doe123@email-domain.co.uk"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("info1234@emailservice.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("support@company.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("contact.us@emailprovider.org"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("marketing.department@emailservice.net"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("testuser1234@email-domain.co"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("info.sales@emailservice.co.uk"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("johndoe42@example.org"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("alice.smith@emaildomain.com"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("webmaster123@testdomain.net"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("billing.inquiries@emailprovider.org"));
        Assertions.assertTrue(CustomerEmailIdValidator.isValidEmail("feedback123@emailservice.com"));
    }

    @Test
    void shouldBeAbleToReturnFalseForAllValidEmails() throws InvalidCustomerEmailException {
        // act && assert
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@example"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@example."));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid email@example.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid/email@example.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@exa mple.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@example,com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@exa_mple.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@example com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@exa$mple.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@exa;mple.com"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invalid_email@[127.0.0.1]"));
        Assertions.assertFalse(CustomerEmailIdValidator.isValidEmail("invaid_email@[IPv6:2001:db8::1]"));
    }
}