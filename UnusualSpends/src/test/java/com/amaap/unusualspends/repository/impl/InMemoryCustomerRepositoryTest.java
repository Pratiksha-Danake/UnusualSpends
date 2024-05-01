package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerNameException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemoryCustomerRepositoryTest {
    InMemoryCustomerRepository inMemoryCustomerRepository;
    @BeforeAll
    void setUp(){
        Injector injector = Guice.createInjector(new AppModule());
        inMemoryCustomerRepository = injector.getInstance(InMemoryCustomerRepository.class);
    }

    @Test
    void shouldBeAbleToAddCustomerToDatabase() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        int id = 1;
        String customerName = "John Doe";
        String email = "johndoe@gmail.com";

        // act
        Customer customerToAdd = Customer.create(id,customerName,email);
        Customer customerAdded = inMemoryCustomerRepository.addCustomer(customerToAdd);

        // assert
        assertEquals(customerToAdd, customerAdded);
    }
}
