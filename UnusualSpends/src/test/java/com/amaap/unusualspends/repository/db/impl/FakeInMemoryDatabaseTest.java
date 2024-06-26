package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeInMemoryDatabaseTest {
    CreditCardRepository creditCardRepository;
    CustomerRepository customerRepository;
    InMemoryDatabase inMemoryDatabase;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardRepository = injector.getInstance(InMemoryCreditCardRepository.class);
        customerRepository = injector.getInstance(InMemoryCustomerRepository.class);
        inMemoryDatabase = injector.getInstance(FakeInMemoryDatabase.class);
    }

    @Test
    void shouldBeAbleToAddCustomerIntoCustomerTable() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(2, "Pratiksha Danake", "pratiksha@gmail.com");

        // act
        Customer customerAdded = customerRepository.addCustomer(customerToAdd);

        // assert
        assertEquals(customerToAdd, customerAdded);
    }

    @Test
    void shouldThrowCustomerAlreadyExistsExceptionIfCustomerWithSameDetailsFoundInDatabase() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        Customer customerToAdd = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");

        // act && assert
        customerRepository.addCustomer(customerToAdd);
        assertThrows(CustomerAlreadyExistsException.class, () -> {
            customerRepository.addCustomer(customerToAdd);
        });
    }

    @Test
    void shouldBeAbleToAddCreditCardIntoDatabase() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long cardId = 1;
        CreditCard creditCardToAdd = CreditCard.create(cardId, customer);

        // act
        CreditCard cardAdded = creditCardRepository.addCreditCard(creditCardToAdd);

        // assert
        assertEquals(creditCardToAdd, cardAdded);
    }

    @Test
    void shouldBeAbleToAddTransactionDetailsToTheDatabase() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 100;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);

        // act
        Transaction transactionToAdd = Transaction.create(transactionId, cardId, category, amountSpend, transactionOnDate);
        Transaction transactionAdded = inMemoryDatabase.addTransaction(transactionToAdd);

        // assert
        assertEquals(transactionToAdd, transactionAdded);
    }

    @Test
    void shouldBeAbleToFindCustomerById() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        int customerId = 1;
        String customerName = "Pratiksha Danake";
        String customerEmail = "pratiksha@gmail.com";

        // act
        Customer expected = Customer.create(customerId, customerName, customerEmail);
        inMemoryDatabase.addCustomer(expected);
        Customer actual = inMemoryDatabase.findCustomerBy(customerId);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToReturnNullIfCustomerWithGivenIdIsNotPresent() throws InvalidCustomerException, CustomerAlreadyExistsException {
        // arrange
        int customerId = 2;
        String customerName = "Pratiksha Danake";
        String customerEmail = "pratiksha@gmail.com";

        // act
        Customer customer = Customer.create(customerId, customerName, customerEmail);
        inMemoryDatabase.addCustomer(customer);
        Customer actual = inMemoryDatabase.findCustomerBy(10);

        // assert
        assertNull(actual);
    }

    @Test
    void shouldBeAbleToGetCreditCardById() throws InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Customer customer = Customer.create(1, "Pratiksha Danake", "pratiksha@gmail.com");
        long id = 1;

        // act
        CreditCard expectedCreditCard = CreditCard.create(1, customer);
        inMemoryDatabase.addCreditCard(expectedCreditCard);
        CreditCard actualCreditCard = inMemoryDatabase.getCreditCardBy(id);

        // assert
        assertEquals(expectedCreditCard, actualCreditCard);
    }

    @Test
    void shouldBeAbleToReturnNullIfCreditCardForGivenIdIsNotPresent() {
        // arrange
        long id = 2;

        // act
        CreditCard creditCard = inMemoryDatabase.getCreditCardBy(id);

        // assert
        assertNull(creditCard);
    }

    @Test
    void shouldBeAbleToGetAllTransactionsForGivenCreditCard() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction transaction1 = Transaction.create(1, 1, Category.BOOKS, 100, transactionOnDate);
        Transaction transaction2 = Transaction.create(2, 1, Category.GROCERIES, 120, transactionOnDate);
        List<Transaction> expectedList = List.of(transaction1, transaction2);

        // act
        inMemoryDatabase.addTransaction(transaction1);
        inMemoryDatabase.addTransaction(transaction2);
        List<Transaction> actualList = inMemoryDatabase.getAllTransactions();

        // assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void shouldBeAbleToReturnNullIfThereIsNoAnyTransactionAssociatedWithTheCreditCard() {
        // arrange && act
        List<Transaction> transactions = inMemoryDatabase.getAllTransactions();

        // assert
        assertNull(transactions);
    }
}