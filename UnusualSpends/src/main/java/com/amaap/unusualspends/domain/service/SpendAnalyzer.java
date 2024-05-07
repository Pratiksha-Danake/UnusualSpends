package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpendAnalyzer {
    Map<Long, List<SpendDto>> spendRecords = new HashMap<>();

    public Map<Long, List<SpendDto>> analyzeSpend(List<Transaction> currentMonthTransactions,
                                                  List<Transaction> previousMonthTransactions,
                                                  double thresholdPercentage) {

        double criteria = 1 + (thresholdPercentage / 100);
        for (Transaction currentTransaction : currentMonthTransactions) {
            for (Transaction previousTransaction : previousMonthTransactions) {
                if (currentTransaction.getCategory() == previousTransaction.getCategory() &&
                        currentTransaction.getCardId() == previousTransaction.getCardId() &&
                        currentTransaction.getAmountSpend() > previousTransaction.getAmountSpend() * criteria) {

                    long cardId = currentTransaction.getCardId();
                    Category category = currentTransaction.getCategory();
                    double usualSpendAmount = previousTransaction.getAmountSpend();
                    double unusualSpendAmount = currentTransaction.getAmountSpend() - usualSpendAmount;
                    SpendDto spendRecord = new SpendDto(category, unusualSpendAmount, usualSpendAmount);
                    List<SpendDto> spendRecordList;
                    if (spendRecords.containsKey(cardId)) {
                        spendRecordList = spendRecords.get(cardId);

                    } else {
                        spendRecordList = new ArrayList<>();
                    }
                    spendRecordList.add(spendRecord);
                    spendRecords.put(cardId, spendRecordList);
                }
            }
        }
        return spendRecords;
    }
}
