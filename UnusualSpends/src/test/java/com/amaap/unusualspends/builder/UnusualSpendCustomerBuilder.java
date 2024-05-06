package com.amaap.unusualspends.builder;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnusualSpendCustomerBuilder {
    public static Map<Long, List<SpendsDto>> getUnusualSpendCustomers() {
        Long cardId = Long.valueOf(1);
        Map<Long, List<SpendsDto>> unusualSpendCustomers = new HashMap<>();
        List<SpendsDto> spendDtos = new ArrayList<>();
        spendDtos.add(new SpendsDto(Category.GROCERIES, 300, 100));
        spendDtos.add(new SpendsDto(Category.TRAVEL, 400, 200));
        unusualSpendCustomers.put(cardId, spendDtos);
        return unusualSpendCustomers;
    }
}