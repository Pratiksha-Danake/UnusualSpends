package com.amaap.unusualspends.builder;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnusualSpendCustomerBuilder {
    public static Map<Long, List<SpendDto>> getUnusualSpendCustomers() {
        Long cardId = Long.valueOf(1);
        Map<Long, List<SpendDto>> unusualSpendCustomers = new HashMap<>();
        List<SpendDto> spendDtos = new ArrayList<>();
        spendDtos.add(new SpendDto(Category.GROCERIES, 300, 100));
        spendDtos.add(new SpendDto(Category.TRAVEL, 400, 200));
        unusualSpendCustomers.put(cardId, spendDtos);
        return unusualSpendCustomers;
    }
}