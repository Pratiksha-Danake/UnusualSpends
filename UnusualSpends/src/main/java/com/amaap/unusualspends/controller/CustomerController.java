package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.amaap.unusualspends.service.CustomerService;
import com.google.inject.Inject;

public class CustomerController {

    private final CustomerService customerService;

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Response createCustomer(String customerName, String email) throws InvalidCustomerException, CustomerAlreadyExistsException {
        if (customerService.createCustomerToAdd(customerName, email) != null)
            return new Response(HttpStatus.OK, "Customer Created");
        return new Response(HttpStatus.ERROR_OCCURED, "Error While Creating Customer");
    }

    public Customer findCustomerBy(int id) {
        return customerService.findCustomerBy(id);
    }
}
