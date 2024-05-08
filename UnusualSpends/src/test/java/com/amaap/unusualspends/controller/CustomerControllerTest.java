package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        customerController = injector.getInstance(CustomerController.class);
    }

    @Test
    void shouldBeAbleToReturnResponseAsOKIfCreatesCustomerSuccessfully() {
        // arrange
        Response expected = new Response(HttpStatus.OK, "Customer Created");
        String customerName = "John Doe";
        String email = "johndoe@gmail.com";

        // act
        Response actual = customerController.createCustomer(customerName, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToReturnResponseAsBadRequestIfCustomerDataIsInvalid() {
        // arrange
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid Customer Data");
        String customerName = "John Do";
        String email = "johndoe@gmail.com";

        // act
        Response actual = customerController.createCustomer(customerName, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToFindCustomerById() throws InvalidCustomerException {
        // arrange
        int id = 1;
        String name = "Pratiksha Danake";
        String email = "pratiksha@gmail.com";
        Customer expected = Customer.create(id, name, email);

        // act
        customerController.createCustomer(name, email);
        customerController.createCustomer(name, email);
        Customer actual = customerController.findCustomerBy(id);

        // assert
        assertEquals(expected, actual);
    }
}
