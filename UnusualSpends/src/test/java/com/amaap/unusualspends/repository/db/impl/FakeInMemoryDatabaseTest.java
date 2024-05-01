package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FakeInMemoryDatabaseTest {
    FakeInMemoryDatabase fakeInMemoryDatabase;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        fakeInMemoryDatabase = injector.getInstance(FakeInMemoryDatabase.class);
    }

    @Test
    void shouldBeAbleToAddCustomerIntoCustomerTable() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(2, "Pratiksha Danake", "pratiksha@gmail.com");

        // act
        Customer customerAdded = fakeInMemoryDatabase.addCustomer(customerToAdd);

        // assert
        assertEquals(customerToAdd, customerAdded);
    }

    @Test
    void shouldThrowCustomerAlreadyExistsExceptionIfCustomerWithSameDetailsFoundInDatabase() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");

        // act && assert
        fakeInMemoryDatabase.addCustomer(customerToAdd);
        assertThrows(CustomerAlreadyExistsException.class, () -> {
            fakeInMemoryDatabase.addCustomer(customerToAdd);
        });
    }

    @Test
    void shouldBeAbleToAddCreditCardIntoDatabase() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long cardId = 1;
        CreditCard creditCardToAdd = CreditCard.create(cardId, customer);

        // act
        CreditCard cardAdded = fakeInMemoryDatabase.addCreditCard(creditCardToAdd);
    }
}