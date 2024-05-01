package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreditCardTest {
    @Test
    void shouldBeAbleToCreateCreditCardWithId() throws InvalidCreditCardIdException, InvalidCustomerException {
        // arrange
        long cardId = 1;
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard expected = new CreditCard(cardId, customer);

        // act
        CreditCard actual = CreditCard.create(cardId, customer);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowInvalidCreditCardIdExceptionIfCreditCardIdIsInvalid() throws InvalidCustomerException {
        // arrange
        long cardId = -1;
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");

        // act && assert
        assertThrows(InvalidCreditCardIdException.class, () -> {
            CreditCard.create(cardId, customer);
        });
    }
}
