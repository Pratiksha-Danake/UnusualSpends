package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.amaap.unusualspends.repository.db.impl.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeInMemoryDatabase implements InMemoryDatabase {
    List<Customer> customers = new ArrayList<>();
    List<CreditCard> cards = new ArrayList<>();

    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
        if (customers.contains(customer))
            throw new CustomerAlreadyExistsException("Customer Found With Same Details..!");
        customers.add(customer);
        return customer;
    }

    public CreditCard addCreditCard(CreditCard creditCardToAdd) {
       cards.add(creditCardToAdd);
       return creditCardToAdd;
    }
}
