package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction addTransaction(Transaction transactionToAdd);

    List<Transaction> getAllTransactions();
}
