package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;

public interface CustomerRepository {

    Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
}
