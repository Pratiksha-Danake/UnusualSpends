package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryTransactionRepositoryTest {
    TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        transactionRepository = injector.getInstance(InMemoryTransactionRepository.class);
    }

    @Test
    void shouldBeAbleToAddTransactionToDatabase() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 100;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);

        // act
        Transaction transactionToAdd = Transaction.create(transactionId, cardId, category, amountSpend, transactionOnDate);
        Transaction added = transactionRepository.addTransaction(transactionToAdd);
    }

    @Test
    void shouldBeAbleToGetTransactionAssociatedWithTheCreditCard() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction transaction1 = Transaction.create(1, 1, Category.BOOKS, 100, transactionOnDate);
        Transaction transaction2 = Transaction.create(2, 1, Category.GROCERIES, 120, transactionOnDate);
        List<Transaction> expectedList = List.of(transaction1, transaction2);

        // act
        transactionRepository.addTransaction(transaction1);
        transactionRepository.addTransaction(transaction2);
        List<Transaction> actualList = transactionRepository.getAllTransactions();

        // assert
        assertEquals(expectedList, actualList);
    }
}
