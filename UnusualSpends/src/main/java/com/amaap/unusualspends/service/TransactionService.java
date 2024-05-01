package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.time.LocalDate;

public class TransactionService {
    private int transactionId = 0;

    public Transaction createTransaction(long cardId, Category category, double amountSpend, LocalDate transactionDate) throws InvalidTransactionCategory, InvalidTransactionAmount {
        transactionId++;
        return Transaction.create(transactionId,cardId,category,amountSpend,transactionDate);
    }
}
