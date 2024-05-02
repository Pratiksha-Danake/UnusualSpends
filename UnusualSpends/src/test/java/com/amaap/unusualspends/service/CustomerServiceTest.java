package com.amaap.unusualspends.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest {
    CustomerService customerService;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        customerService = injector.getInstance(CustomerService.class);
    }

    @Test
    void shouldBeAbleToCreateCustomerToAddItToDatabase() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        String customerName = "John Doe";
        String email = "johndoe@gmail.com";

        // act
        Customer customerAdded = customerService.createCustomerToAdd(customerName, email);
        Customer customerToAdd = Customer.create(customerAdded.getId(), customerName, email);
        System.out.println(customerAdded.getId() + "  " + customerAdded.getEmail() + "  " + customerAdded.getName());

        // assert
        assertEquals(customerToAdd, customerAdded);
    }

    @Test
    void shouldBeAbleToFindCustomerById() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        int customerId = 1;
        String customerName = "Pratiksha Danake";
        String customerEmail = "pratiksha@gmail.com";

        // act
        Customer expected = customerService.createCustomerToAdd(customerName, customerEmail);
        Customer actual = customerService.findCustomerBy(expected.getId());

        // assert
        assertEquals(expected, actual);
    }
}
