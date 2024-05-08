package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryWiseSpendAnalyzer {
    Map<Long, List<SpendDto>> categoryWiseSpendRecords = new HashMap<>();

    public Map<Long, List<SpendDto>> analyzeSpend(List<Transaction> currentMonthTransactions,
                                                  List<Transaction> previousMonthTransactions,
                                                  double thresholdPercentage) {

        double criteria = 1 + (thresholdPercentage * 0.01);

        for (Transaction currentTransaction : currentMonthTransactions) {
            for (Transaction previousTransaction : previousMonthTransactions) {
                if (currentTransaction.getCategory() == previousTransaction.getCategory() &&
                        currentTransaction.getCardId() == previousTransaction.getCardId() &&
                        currentTransaction.getAmountSpend() > previousTransaction.getAmountSpend() * criteria) {

                    long cardId = currentTransaction.getCardId();
                    Category category = currentTransaction.getCategory();
                    double usualAmountSpend = previousTransaction.getAmountSpend();
                    double unusualAmountSpend = currentTransaction.getAmountSpend() - usualAmountSpend;
                    SpendDto spendRecord = new SpendDto(category, usualAmountSpend, unusualAmountSpend);
                    List<SpendDto> categoryWiseSpendList;
                    if (categoryWiseSpendRecords.containsKey(cardId)) {
                        categoryWiseSpendList = categoryWiseSpendRecords.get(cardId);

                    } else {
                        categoryWiseSpendList = new ArrayList<>();
                    }
                    categoryWiseSpendList.add(spendRecord);
                    categoryWiseSpendRecords.put(cardId, categoryWiseSpendList);
                }
            }
        }
        return categoryWiseSpendRecords;
    }
}
