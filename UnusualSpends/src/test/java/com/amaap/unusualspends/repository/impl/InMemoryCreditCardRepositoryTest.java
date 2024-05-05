package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryCreditCardRepositoryTest {
    InMemoryCreditCardRepository inMemoryCreditCardRepository = new InMemoryCreditCardRepository(new FakeInMemoryDatabase());

    @Test
    void shouldBeAbleToAddCreditCardIntoTheDatabase() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long cardId = 1;
        CreditCard creditCardToAdd = CreditCard.create(cardId, customer);

        // act
        CreditCard creditCardAdded = inMemoryCreditCardRepository.addCreditCard(creditCardToAdd);

        // assert
        assertEquals(creditCardToAdd, creditCardAdded);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long id = 1;

        // act
        CreditCard expectedCreditCard = CreditCard.create(1, customer);
        inMemoryCreditCardRepository.addCreditCard(expectedCreditCard);
        CreditCard actualCreditCard = inMemoryCreditCardRepository.getCreditCardBy(id);

        // assert
        assertEquals(expectedCreditCard, actualCreditCard);
    }
}
