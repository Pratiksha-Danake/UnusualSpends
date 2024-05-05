package com.amaap.unusualspends.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreditCardServiceTest {
    CreditCardService creditCardService;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardService = injector.getInstance(CreditCardService.class);
    }

    @Test
    void shouldBeAbleToAddCreditCardToDatabase() throws InvalidCreditCardIdException, InvalidCustomerException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long cardId = 1;
        CreditCard expected = CreditCard.create(cardId, customer);

        // act
        CreditCard creditCardAdded = creditCardService.createCreditCardFor(customer);

        // assert
        assertEquals(expected, creditCardAdded);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long id = 1;

        // act
        CreditCard expectedCreditCard = CreditCard.create(1, customer);
        creditCardService.createCreditCardFor(customer);
        CreditCard actualCreditCard = creditCardService.getCreditCardBy(id);

        // assert
        assertEquals(expectedCreditCard, actualCreditCard);
    }
}
