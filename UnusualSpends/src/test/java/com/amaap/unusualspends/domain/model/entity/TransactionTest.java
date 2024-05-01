package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

public class TransactionTest {

    @Test
    void shouldBeAbleToCreateTransactionWithDetails() {
        // arrange
        long cardId = 1;
        long transactionId = 1;
        Category category = Category.BOOKS;
        double amountSpend = 100;
        LocalDate transactionOnDate = LocalDate.of(2024, Month.MAY, 1);
        Transaction expected = Transaction.create(transactionId, cardId, category, amountSpend, transactionOnDate);

        // act
        Transaction actual = Transaction.create(transactionId, cardId, category, amountSpend, transactionOnDate);
    }
}
