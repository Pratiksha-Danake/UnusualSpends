package com.amaap.unusualspends.repository.db.impl;

import com.amaap.unusualspends.domain.model.entity.Customer;

public interface InMemoryDatabase {
    Customer addCustomer(Customer customer);
}
