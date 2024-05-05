package com.amaap.unusualspends.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
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
    void shouldBeAbleToCreateTransactionToAddItToTheDatabase() throws InvalidTransactionCategory, InvalidTransactionAmount {
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
    void shouldBeAbleToAddTransactionToDatabase() throws InvalidTransactionCategory, InvalidTransactionAmount {
        // arrange
        long cardId = 1;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction transaction1 = Transaction.create(1, 1, Category.BOOKS, 100, transactionOnDate);
        Transaction transaction2 = Transaction.create(2, 1, Category.GROCERY, 120, transactionOnDate);
        List<Transaction> expectedList = List.of(transaction1, transaction2);

        // act
        transactionService.createTransaction(1, Category.BOOKS, 100, transactionOnDate);
        transactionService.createTransaction(1, Category.GROCERY, 120, transactionOnDate);
        List<Transaction> actualList = transactionService.getTransactionForCreditCard(cardId);

        // assert
        assertEquals(expectedList, actualList);
    }
}
