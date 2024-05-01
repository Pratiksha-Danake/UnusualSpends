package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest {
    private CustomerController customerController;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        customerController = injector.getInstance(CustomerController.class);
    }

    @Test
    void shouldBeAbleToReturnResponseAsOKIfCreatesCustomerSuccessfully() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Response expected = new Response(HttpStatus.OK, "Customer Created");
        String customerName = "John Doe";
        String email = "johndoe@gmail.com";

        // act
        Response actual = customerController.createCustomer(customerName, email);
    }
}
