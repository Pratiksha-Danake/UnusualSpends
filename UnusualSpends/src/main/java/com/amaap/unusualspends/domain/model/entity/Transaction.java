package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private long id;
    private long cardId;
    private Category category;
    private double amountSpend;
    private LocalDate transactionDate;

    public Transaction(long id, long cardId, Category category, double amountSpend, LocalDate transactionDate) {
        this.id = id;
        this.cardId = cardId;
        this.category = category;
        this.amountSpend = amountSpend;
        this.transactionDate = transactionDate;
    }

    public static Transaction create(long id, long cardId, Category category, double amountSpend, LocalDate transactionDate) {
        return new Transaction(id, cardId, category, amountSpend, transactionDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && cardId == that.cardId && Double.compare(that.amountSpend, amountSpend) == 0 && category == that.category && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardId, category, amountSpend, transactionDate);
    }
}
