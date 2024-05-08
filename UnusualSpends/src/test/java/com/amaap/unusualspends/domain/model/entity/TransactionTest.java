package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    void shouldBeAbleToCreateTransactionWithDetails() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
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
    void shouldBeAbleToThrowInvalidCategoryExceptionIfTransactionCategoryIsNull() {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = null;
        double amountSpend = 100;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);

        // act && assert
        assertThrows(InvalidTransactionCategoryException.class, () -> {
            Transaction.create(cardId, transactionId, category, amountSpend, transactionDate);
        });
    }

    @Test
    void shouldBeAbleToThrowInvalidTransactionAmountExceptionExceptionIfTransactionAmountIsInvalid() {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 0;
        LocalDate transactionDate = LocalDate.of(2024, Month.MAY, 1);

        // act && assert
        assertThrows(InvalidTransactionAmountException.class, () -> {
            Transaction.create(cardId, transactionId, category, amountSpend, transactionDate);
        });
    }

    @Test
    public void shouldBeAbleToReturnSameHashCodeFor() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        int id = 1;
        long cardId = 1;
        Category category = Category.SHOPPING;
        double amountSpend = 500.0;
        LocalDate transactionDate = LocalDate.of(2022, 5, 10);
        Transaction transaction1 = Transaction.create(id, cardId, category, amountSpend, transactionDate);
        Transaction transaction2 = Transaction.create(id, cardId, category, amountSpend, transactionDate);

        // act
        int hashCode1 = transaction1.hashCode();
        int hashCode2 = transaction2.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void shouldBeAbleToReturnTrueWhenComparesSameInstance() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Transaction transaction = Transaction.create(1, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));

        // act & assert
        assertTrue(transaction.equals(transaction));
    }

    @Test
    void shouldBeAbleToReturnFalseWhenComparesWithNull() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Transaction transaction = Transaction.create(1, 123, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));

        // act & assert
        assertFalse(transaction.equals(null));
    }

    @Test
    void shouldBeAbleToReturnFalseWhenComparesWithOtherClass() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Transaction transaction = Transaction.create(1, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));

        // act & assert
        assertFalse(transaction.equals("String"));
    }

    @Test
    void shouldBeAbleToReturnTrueWhenComparesTwoInstancesWithSameFieldValues() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Transaction transaction1 = Transaction.create(1, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));
        Transaction transaction2 = Transaction.create(1, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));

        // act & assert
        assertTrue(transaction1.equals(transaction2));
    }

    @Test
    void shouldBeAbleToReturnTrueWhenComparesTwoInstancesWithDifferentFieldValues() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Transaction transaction1 = Transaction.create(1, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));
        Transaction transaction2 = Transaction.create(2, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));

        //act & assert
        assertFalse(transaction1.equals(transaction2));
    }

    @Test
    void shouldBeAbleToReturnIdOfTheTransaction() throws InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Transaction transaction = Transaction.create(1, 1, Category.SHOPPING, 500.0, LocalDate.of(2022, 5, 10));
        int expected = 1;

        //act & assert
        assertEquals(expected,transaction.getId());
    }
}
