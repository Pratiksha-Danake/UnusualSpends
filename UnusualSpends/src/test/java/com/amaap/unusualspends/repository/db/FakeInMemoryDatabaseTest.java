package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FakeInMemoryDatabaseTest {
    @Test
    void shouldBeAbleToAddCustomerIntoCustomerTable() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        FakeInMemoryDatabase fakeInMemoryDatabase = new FakeInMemoryDatabase();

        // act
        Customer customerAdded = fakeInMemoryDatabase.addCustomer(customerToAdd);

        // assert
        assertEquals(customerToAdd, customerAdded);
    }

    @Test
    void shouldThrowCustomerAlreadyExistsExceptionIfCustomerWithSameDetailsFoundInDatabase() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        FakeInMemoryDatabase fakeInMemoryDatabase = new FakeInMemoryDatabase();

        // act && assert
        fakeInMemoryDatabase.addCustomer(customerToAdd);
        assertThrows(CustomerAlreadyExistsException.class,()->{
            fakeInMemoryDatabase.addCustomer(customerToAdd);
        });
    }
}