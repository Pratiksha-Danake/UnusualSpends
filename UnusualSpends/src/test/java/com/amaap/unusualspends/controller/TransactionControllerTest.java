package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionControllerTest {
    TransactionController transactionController;

    @BeforeAll
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        transactionController = injector.getInstance(TransactionController.class);
    }

    @Test
    void shouldBeAbleToReturnResponseAsOKIfCreatesTransactionSuccessfully() throws InvalidTransactionCategory, InvalidTransactionAmount {
        // arrange
        int transactionId = 1;
        long cardId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 100;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);
        Response expected = new Response(HttpStatus.OK, "Transaction Created");

        // act
        Response actual = transactionController.createTransaction(cardId, category, amountSpend, transactionDate);

        // assert
        assertEquals(expected, actual);
    }
}
