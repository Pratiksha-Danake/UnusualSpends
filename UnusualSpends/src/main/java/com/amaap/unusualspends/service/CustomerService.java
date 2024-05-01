package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.google.inject.Inject;

public class CustomerService {
    private CustomerRepository customerRepository;
    private int customerId = 0;

    @Inject
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomerToAdd(String customerName, String email) throws InvalidCustomerException, CustomerAlreadyExistsException {
        customerId++;
        Customer customer = Customer.create(customerId, customerName, email);
        return customerRepository.addCustomer(customer);
    }
}
