package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.Month;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemoryTransactionRepositoryTest {
    TransactionRepository transactionRepository;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        transactionRepository = injector.getInstance(InMemoryTransactionRepository.class);
    }

    @Test
    void shouldBeAbleToAddTransactionToDatabase() throws InvalidTransactionCategory, InvalidTransactionAmount {
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
}
