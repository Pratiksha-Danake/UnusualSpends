package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.service.CreditCardCompanyService;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class CreditCardCompanyController {
    private CreditCardCompanyService creditCardCompanyService;

    @Inject
    public CreditCardCompanyController(CreditCardCompanyService creditCardCompanyService) {
        this.creditCardCompanyService = creditCardCompanyService;
    }

    public  Map<Long, List<SpendsDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return creditCardCompanyService.analyzeSpend(currentMonthTransactions,previousMonthTransactions,thresholdPercentage);
    }

    public boolean sendEmail(Map<Long, List<SpendsDto>> spendRecord) {
        return creditCardCompanyService.sendEmail(spendRecord);
    }
}
