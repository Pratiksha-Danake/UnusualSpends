package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TransactionControllerTest {
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        transactionController = injector.getInstance(TransactionController.class);
    }

    @Test
    void shouldBeAbleToReturnResponseAsOKIfCreatesTransactionSuccessfully() {
        // arrange
        long cardId = 1;
        Category category = Category.SHOPPING;
        double amountSpend = 100;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);
        Response expected = new Response(HttpStatus.OK, "Transaction Created");

        // act
        Response actual = transactionController.createTransaction(cardId, category, amountSpend, transactionDate);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToReturnResponseAsBadRequestIfTransactionAmountIsNotValid() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        Category category = Category.SHOPPING;
        double amountSpend = -1000;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction Amount");

        // act
        Response actual = transactionController.createTransaction(cardId, category, amountSpend, transactionDate);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToReturnResponseAsBadRequestIfTransactionCategoryIsNotValid() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        Category category = null;
        double amountSpend = 1000;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction Category");

        // act
        Response actual = transactionController.createTransaction(cardId, category, amountSpend, transactionDate);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToAddTransactionToDatabase() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction transaction1 = Transaction.create(1, 1, Category.SHOPPING, 100, transactionOnDate);
        Transaction transaction2 = Transaction.create(2, 1, Category.BOOKS, 120, transactionOnDate);
        List<Transaction> expectedList = List.of(transaction1, transaction2);

        // act
        transactionController.createTransaction(1, Category.SHOPPING, 100, transactionOnDate);
        transactionController.createTransaction(1, Category.BOOKS, 120, transactionOnDate);
        List<Transaction> actualList = transactionController.getAllTransactions();

        // assert
        assertEquals(expectedList, actualList);
    }

    @Test
    void shouldBeAbleToFilterTransactionByMonth() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        long cardId = 1;
        Transaction transaction1 = Transaction.create(1, 1, Category.BOOKS, 100, LocalDate.of(2024, Month.MAY, 24));
        List<Transaction> expectedList = List.of(transaction1);

        // act
        transactionController.createTransaction(1, Category.BOOKS, 100, LocalDate.of(2024, Month.MAY, 24));
        transactionController.createTransaction(1, Category.GROCERIES, 120, LocalDate.of(2024, Month.APRIL, 24));
        List<Transaction> actualList = transactionController.getTransactionsByMonth(Month.MAY);

        // assert
        assertEquals(expectedList, actualList);
    }
}