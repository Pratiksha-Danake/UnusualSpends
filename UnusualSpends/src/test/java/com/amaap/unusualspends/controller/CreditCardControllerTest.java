package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
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
public class CreditCardControllerTest {
    CreditCardController creditCardController;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardController = injector.getInstance(CreditCardController.class);
    }

    @Test
    void shouldBeAbleToReturnResponseAsOKIfAddsCreditCardDetailsSuccessfully() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        Response expected = new Response(HttpStatus.OK, "Card Created");

        // act
        Response actual = creditCardController.createCreditCardFor(customer);

        // assert
        assertEquals(expected, actual);
    }
}
