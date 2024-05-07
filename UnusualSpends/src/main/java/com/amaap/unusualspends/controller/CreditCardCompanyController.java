package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.service.dto.SpendDto;
import com.amaap.unusualspends.service.CreditCardCompanyService;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class CreditCardCompanyController {
    private final CreditCardCompanyService creditCardCompanyService;

    @Inject
    public CreditCardCompanyController(CreditCardCompanyService creditCardCompanyService) {
        this.creditCardCompanyService = creditCardCompanyService;
    }

    public Map<Long, List<SpendDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return creditCardCompanyService.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
    }

    public Response sendEmail(Map<Long, List<SpendDto>> spendRecord) {
        if (creditCardCompanyService.sendEmail(spendRecord))
            return new Response(HttpStatus.OK, "Email Sent");
        return new Response(HttpStatus.ERROR_OCCURED, "Error While Sending Email");
    }
}
