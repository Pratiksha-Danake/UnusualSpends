package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.service.SpendAnalyzer;
import com.amaap.unusualspends.domain.service.dto.UnusualSpendCustomer;
import com.google.inject.Inject;

import java.util.List;

public class CreditCardCompanyService {
    private SpendAnalyzer spendAnalyzer;

    @Inject
    public CreditCardCompanyService(SpendAnalyzer spendAnalyzer) {
        this.spendAnalyzer = spendAnalyzer;
    }

    public List<UnusualSpendCustomer> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return spendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
    }
}
