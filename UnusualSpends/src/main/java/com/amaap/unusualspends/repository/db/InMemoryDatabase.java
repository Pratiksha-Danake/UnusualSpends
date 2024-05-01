package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;

public interface InMemoryDatabase {
    Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
    CreditCard addCreditCard(CreditCard creditCardToAdd);
}