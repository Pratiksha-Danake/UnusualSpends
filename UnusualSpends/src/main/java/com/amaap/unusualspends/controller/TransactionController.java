package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
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

    public Response createTransaction(long cardId, Category category, double amountSpend, LocalDate transactionDate) {
        Response responseToSend = null;
        try {
            if (transactionService.createTransaction(cardId, category, amountSpend, transactionDate) != null)
                responseToSend = new Response(HttpStatus.OK, "Transaction Created");
        } catch (InvalidTransactionAmountException e) {
            responseToSend = new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction Amount");
        } catch (InvalidTransactionCategoryException e) {
            responseToSend = new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction Category");
        }
        return responseToSend;
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    public List<Transaction> getTransactionsByMonth(Month month) {
        return getAllTransactions().stream().filter(transaction -> transaction.getTransactionDate().getMonth().equals(month)).collect(Collectors.toList());
    }
}
