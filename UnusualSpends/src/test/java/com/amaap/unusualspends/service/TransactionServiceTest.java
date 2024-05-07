package com.amaap.unusualspends.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTest {
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        transactionService = injector.getInstance(TransactionService.class);
    }

    @Test
    void shouldBeAbleToCreateTransactionToAddItToTheDatabase() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        int transactionId = 1;
        long cardId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 100;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);

        // act
        Transaction transactionToCreate = Transaction.create(transactionId, cardId, category, amountSpend, transactionDate);
        Transaction transactionCreated = transactionService.createTransaction(cardId, category, amountSpend, transactionDate);

        // assert
        assertEquals(transactionToCreate, transactionCreated);
    }

    @Test
    void shouldBeAbleToAddTransactionToDatabase() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction transaction1 = Transaction.create(1, 1, Category.BOOKS, 100, transactionOnDate);
        Transaction transaction2 = Transaction.create(2, 1, Category.GROCERIES, 120, transactionOnDate);
        List<Transaction> expectedList = List.of(transaction1, transaction2);

        // act
        transactionService.createTransaction(1, Category.BOOKS, 100, transactionOnDate);
        transactionService.createTransaction(1, Category.GROCERIES, 120, transactionOnDate);
        List<Transaction> actualList = transactionService.getAllTransactions();

        // assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void shouldBeAbleToFilterTransactionByMonth() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        Transaction transaction1 = Transaction.create(1, 1, Category.BOOKS, 100, LocalDate.of(2024, Month.MAY, 1));
        List<Transaction> expectedList = List.of(transaction1);

        // act
        transactionService.createTransaction(1, Category.BOOKS, 100, LocalDate.of(2024, Month.MAY, 1));
        transactionService.createTransaction(1, Category.GROCERIES, 120, LocalDate.of(2024, Month.APRIL, 1));
        List<Transaction> actualList = transactionService.getTransactionsByMonth(Month.MAY);

        // assert
        assertEquals(expectedList, actualList);
    }
}
