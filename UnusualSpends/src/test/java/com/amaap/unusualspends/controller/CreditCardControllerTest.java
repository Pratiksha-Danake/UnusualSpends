package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardControllerTest {
    CreditCardController creditCardController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardController = injector.getInstance(CreditCardController.class);
    }

    @Test
    void shouldBeAbleToReturnResponseAsOKIfAddsCreditCardDetailsSuccessfully() throws InvalidCustomerException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        Response expected = new Response(HttpStatus.OK, "Card Created");

        // act
        Response actual = creditCardController.createCreditCardFor(customer);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long id = 1;

        // act
        CreditCard expectedCreditCard = CreditCard.create(1, customer);
        creditCardController.createCreditCardFor(customer);
        CreditCard actualCreditCard = creditCardController.getCreditCardBy(id);

        // assert
        assertEquals(expectedCreditCard, actualCreditCard);
    }
}
