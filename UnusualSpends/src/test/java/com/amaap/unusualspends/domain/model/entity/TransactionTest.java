package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {

    @Test
    void shouldBeAbleToCreateTransactionWithDetails() throws InvalidTransactionCategory, InvalidTransactionAmount {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 100;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction expected = Transaction.create(transactionId, cardId, category, amountSpend, transactionOnDate);

        // act
        Transaction actual = Transaction.create(transactionId, cardId, category, amountSpend, transactionOnDate);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToThrowInvalidCategoryExceptionIfTransactionCategoryIsNull() throws InvalidTransactionCategory {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = null;
        double amountSpend = 100;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);

        // act && assert
        assertThrows(InvalidTransactionCategory.class, () -> {
            Transaction.create(cardId, transactionId, category, amountSpend, transactionDate);
        });
    }

    @Test
    void shouldBeAbleToThrowInvalidTransactionAmountExceptionIfTransactionAmountIsInvalid() throws InvalidTransactionCategory {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 0;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);

        // act && assert
        assertThrows(InvalidTransactionAmount.class, () -> {
            Transaction.create(cardId, transactionId, category, amountSpend, transactionDate);
        });
    }
}
