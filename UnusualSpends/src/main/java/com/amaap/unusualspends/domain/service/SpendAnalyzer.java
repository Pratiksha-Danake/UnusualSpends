package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.domain.service.dto.UnusualSpendCustomer;

import java.util.ArrayList;
import java.util.List;

public class SpendAnalyzer {

    public List<UnusualSpendCustomer> analyzeSpend(List<Transaction> currentMonthTransactions,
                                                   List<Transaction> previousMonthTransactions,
                                                   double thresholdPercentage) {

        double criteria = 1 + (thresholdPercentage / 100);
        List<UnusualSpendCustomer> unusualSpendCustomers = new ArrayList<>();
        extractUnusualSpendCustomers(currentMonthTransactions, previousMonthTransactions, criteria, unusualSpendCustomers);
        return unusualSpendCustomers;
    }

    private static void extractUnusualSpendCustomers(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double criteria, List<UnusualSpendCustomer> unusualSpendCustomers) {
        for (Transaction currentTransaction : currentMonthTransactions) {
            for (Transaction previousTransaction : previousMonthTransactions) {
                if (currentTransaction.getCategory() == previousTransaction.getCategory() &&
                        currentTransaction.getCardId() == previousTransaction.getCardId() &&
                        currentTransaction.getAmountSpend() > previousTransaction.getAmountSpend() * criteria) {

                    long cardId = currentTransaction.getCardId();
                    Category category = currentTransaction.getCategory();
                    double usualAmountSpend = previousTransaction.getAmountSpend();
                    double unusualAmountSpend = currentTransaction.getAmountSpend() - usualAmountSpend;

                    isAdd(unusualSpendCustomers, cardId, category, usualAmountSpend, unusualAmountSpend);
                }
            }
        }
    }

    private static boolean isAdd(List<UnusualSpendCustomer> unusualSpendCustomers, long cardId, Category category, double usualAmountSpend, double unusualAmountSpend) {
        return unusualSpendCustomers.add(new UnusualSpendCustomer(cardId,
                new SpendsDto(category, unusualAmountSpend, usualAmountSpend)));
    }
}
