package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.service.TransactionService;
import com.google.inject.Inject;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionController {
    private final TransactionService transactionService;

    @Inject
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response createTransaction(long cardId, Category category, double amountSpend, LocalDate transactionDate) throws InvalidTransactionCategory, InvalidTransactionAmountException {
        if (transactionService.createTransaction(cardId, category, amountSpend, transactionDate) != null)
            return new Response(HttpStatus.OK, "Transaction Created");
        return new Response(HttpStatus.ERROR_OCCURED, "Error While Creating Transaction");
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    public List<Transaction> getTransactionsByMonth(Month month) {
        return getAllTransactions().stream().filter(transaction -> transaction.getTransactionDate().getMonth().equals(month)).collect(Collectors.toList());
    }
}
