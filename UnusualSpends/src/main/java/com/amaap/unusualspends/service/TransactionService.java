package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.google.inject.Inject;

import java.time.LocalDate;

public class TransactionService {
    private TransactionRepository transactionRepository;
    private int transactionId = 0;

    @Inject
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(long cardId, Category category, double amountSpend, LocalDate transactionDate) throws InvalidTransactionCategory, InvalidTransactionAmount {
        transactionId++;
        Transaction transactionToAdd = Transaction.create(transactionId,cardId,category,amountSpend,transactionDate);
        return transactionRepository.addTransaction(transactionToAdd);
    }
}
