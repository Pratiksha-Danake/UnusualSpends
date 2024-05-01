package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionValidatorTest {
    @Test
    void shouldBeAbleToReturnTrueIfCategoryIsValid(){
        // arrange
        Category category = Category.BOOKS;

        // act && assert
        assertTrue(TransactionValidator.isValidCategory(category));
    }

    @Test
    void shouldBeAbleToReturnFalseIfCategoryIsInvalid(){
        // arrange
        Category category = Category.BOOKS;

        // act && assert
        assertTrue(TransactionValidator.isValidCategory(category));
    }
}
