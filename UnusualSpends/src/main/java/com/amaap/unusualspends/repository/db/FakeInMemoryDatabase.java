package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.impl.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    List<Customer> customers = new ArrayList<>();

    @Override
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }
}
