package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.service.TransactionService;
import com.google.inject.Inject;

import java.time.LocalDate;

public class TransactionController {
    private final TransactionService transactionService;

    @Inject
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response createTransaction(long cardId, Category category, double amountSpend, LocalDate transactionDate) throws InvalidTransactionCategory, InvalidTransactionAmount {
        if (transactionService.createTransaction(cardId, category, amountSpend, transactionDate) != null)
            return new Response(HttpStatus.OK, "Transaction Created");
        return new Response(HttpStatus.ERROR_OCCURED, "Error While Creating Transaction");
    }

    public Transaction getTransactionForCreditCard(long id) {
        return transactionService.getTransactionForCreditCard(id);
    }
}
