package com.amaap.unusualspends.builder;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.domain.service.dto.UnusualSpendCustomer;

import java.util.ArrayList;
import java.util.List;

public class UnusualSpendCustomerBuilder {
    static List<UnusualSpendCustomer> unusualSpendCustomers = new ArrayList<>();

    public static List<UnusualSpendCustomer> getUnusualSpendCustomers() {
        UnusualSpendCustomer customer1 = new UnusualSpendCustomer(1, new SpendsDto(Category.GROCERIES, 300, 100));
        UnusualSpendCustomer customer2 = new UnusualSpendCustomer(1, new SpendsDto(Category.TRAVEL, 400, 200));
        unusualSpendCustomers.add(customer1);
        unusualSpendCustomers.add(customer2);
        return unusualSpendCustomers;
    }
}
//        Credit Card = 1     Category = GROCERIES     UsualAmount = 300.0    UnusualAmount = 100.0
//        Credit Card = 1     Category = TRAVEL   UsualAmount = 400.0 UnusualAmount = 200.0