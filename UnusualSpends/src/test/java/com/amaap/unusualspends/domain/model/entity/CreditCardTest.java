package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreditCardTest {
    @Test
    void shouldBeAbleToCreateCreditCardWithId() throws InvalidCreditCardIdException {
        // arrange
        long cardId = 1;
        CreditCard expected = new CreditCard(cardId);

        // act
        CreditCard actual = CreditCard.create(cardId);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowInvalidCreditCardIdExceptionIfCreditCardIdIsInvalid() {
        // arrange
        long cardId = -1;

        // act && assert
        assertThrows(InvalidCreditCardIdException.class,()->{
            CreditCard.create(cardId);
        });
    }
}
