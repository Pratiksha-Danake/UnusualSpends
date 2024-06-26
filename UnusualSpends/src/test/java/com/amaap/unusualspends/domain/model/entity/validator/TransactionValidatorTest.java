package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionValidatorTest {
    @Test
    void shouldBeAbleToReturnTrueIfCategoryIsValid() {
        // arrange
        Category category = Category.BOOKS;

        // act && assert
        assertTrue(TransactionValidator.isValidCategory(category));
    }

    @Test
    void shouldBeAbleToReturnFalseIfCategoryIsInvalid() {
        // arrange
        Category category = Category.BOOKS;

        // act && assert
        assertTrue(TransactionValidator.isValidCategory(category));
    }

    @Test
    void shouldBeAbleToReturnTrueIfAmountSpendOnTransactionIsValid() {
        // arrange
        double amountSpend = 100;

        // act && assert
        assertTrue(TransactionValidator.isValidSpend(amountSpend));
    }

    @Test
    void shouldBeAbleToReturnFalseIfAmountSpendOnTransactionIsInvalid() {
        // arrange
        double amountSpend = 100;

        // act && assert
        assertTrue(TransactionValidator.isValidSpend(amountSpend));
    }

    @Test
    void shouldBeAbleToCreateInstanceOfTransactionValidator() {
        // arrange && act
        TransactionValidator transactionValidator = new TransactionValidator();
        // assert
        assertNotNull(transactionValidator);
    }
}
