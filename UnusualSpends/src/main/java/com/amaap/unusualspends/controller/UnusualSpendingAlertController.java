package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.service.dto.SpendDto;
import com.amaap.unusualspends.service.UnusualSpendingAlertService;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;


public class UnusualSpendingAlertController {
    private final UnusualSpendingAlertService unusualSpendingAlertService;

    @Inject
    public UnusualSpendingAlertController(UnusualSpendingAlertService unusualSpendingAlertService) {
        this.unusualSpendingAlertService = unusualSpendingAlertService;
    }

    public Map<Long, List<SpendDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return unusualSpendingAlertService.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
    }

    public Response sendEmail(Map<Long, List<SpendDto>> spendRecord) {
        if (unusualSpendingAlertService.sendEmail(spendRecord))
            return new Response(HttpStatus.OK, "Email Sent");
        return new Response(HttpStatus.ERROR_OCCURED, "Error While Sending Email");
    }
}
