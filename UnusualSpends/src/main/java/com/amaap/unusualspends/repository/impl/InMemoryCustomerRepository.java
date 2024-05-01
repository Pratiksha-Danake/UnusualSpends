package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.google.inject.Inject;

public class InMemoryCustomerRepository {
    private FakeInMemoryDatabase fakeInMemoryDatabase;

    @Inject
    public InMemoryCustomerRepository(FakeInMemoryDatabase fakeInMemoryDatabase) {
        this.fakeInMemoryDatabase = fakeInMemoryDatabase;
    }

    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
        return fakeInMemoryDatabase.addCustomer(customer);
    }
}