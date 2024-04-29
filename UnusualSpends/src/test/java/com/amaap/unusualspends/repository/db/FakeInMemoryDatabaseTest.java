package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FakeInMemoryDatabaseTest {
    FakeInMemoryDatabase fakeInMemoryDatabase = new FakeInMemoryDatabase();

    @Test
    void shouldBeAbleToAddCustomerIntoCustomerTable() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");

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
        assertThrows(CustomerAlreadyExistsException.class,()->{
            fakeInMemoryDatabase.addCustomer(customerToAdd);
        });
    }

    @Test
    void shouldBeAbleToAddCreditCardIntoDatabase() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long cardId = 1;
        CreditCard creditCardToAdd = CreditCard.create(cardId,customer);

        // act
        CreditCard cardAdded = fakeInMemoryDatabase.addCreditCard(creditCardToAdd);
    }
}