package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.repository.TransactionRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.google.inject.Inject;

import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final InMemoryDatabase inMemoryDatabase;

    @Inject
    public InMemoryTransactionRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return inMemoryDatabase.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return inMemoryDatabase.getAllTransactions();
    }
}
