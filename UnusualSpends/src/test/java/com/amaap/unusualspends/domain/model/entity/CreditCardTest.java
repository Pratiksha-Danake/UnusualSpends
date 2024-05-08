package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {
    @Test
    void shouldBeAbleToCreateCreditCardWithId() throws InvalidCreditCardIdException, InvalidCustomerException {
        // arrange
        long cardId = 1;
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard expected = CreditCard.create(cardId, customer);

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

    @Test
    void shouldBeAbleToReturnTrueIfCompareWithSameInstance() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard = CreditCard.create(1,customer);

        // act & assert
        assertTrue(creditCard.equals(creditCard));
    }

    @Test
    void shouldBeAbleToReturnFalseIfCompareWithNull() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard = CreditCard.create(1,customer);

        // act & assert
        assertFalse(creditCard.equals(null));
    }

    @Test
    void shouldBeAbleToReturnFalseIfCompareWithDifferentClass() throws InvalidCustomerException,InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard = CreditCard.create(1,customer);

        // act & assert
        assertFalse(creditCard.equals("String"));
    }

    @Test
    void shouldBeAbleToReturnTrueIfIdForTwoInstanceIsSame() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard1 = CreditCard.create(1,customer);
        CreditCard creditCard2 = CreditCard.create(1,customer);

        // act & assert
        assertTrue(creditCard1.equals(creditCard2));
    }

    @Test
    void shouldBeAbleToReturnFalseIfIdForTwoInstanceIsDifferent() throws InvalidCustomerException,InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard1 = CreditCard.create(1,customer);
        CreditCard creditCard2 = CreditCard.create(2,customer);

        // act & assert
        assertFalse(creditCard1.equals(creditCard2));
    }

    @Test
    void shouldBeAbleToCheckEqualityOfHashCodeOfTheSameInstance() throws InvalidCustomerException,InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard = CreditCard.create(1,customer);
        int expectedHashCode = creditCard.hashCode();

        // act
        int actualHashCode = creditCard.hashCode();

        // assert
        assertEquals(expectedHashCode, actualHashCode);
    }

    @Test
    void shouldBeAbleToCheckEqualityIfTwoInstancesAreSame() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "sita@gmail.com");
        CreditCard creditCard1 = CreditCard.create(1,customer);
        CreditCard creditCard2 = CreditCard.create(1,customer);

        // act & assert
        assertEquals(creditCard1.hashCode(), creditCard2.hashCode());
    }
}
