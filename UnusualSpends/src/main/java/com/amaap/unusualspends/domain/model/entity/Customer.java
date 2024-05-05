package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerNameException;
import com.amaap.unusualspends.domain.model.entity.validator.CustomerEmailIdValidator;
import com.amaap.unusualspends.domain.model.entity.validator.CustomerIdValidator;
import com.amaap.unusualspends.domain.model.entity.validator.CustomerNameValidator;

import java.util.Objects;

public class Customer {
    private final int id;
    private final String name;
    private final String email;

    private Customer(int customerId, String customerName, String customerEmail) {
        this.id = customerId;
        this.name = customerName;
        this.email = customerEmail;
    }

    public static Customer create(int customerId, String customerName, String email) throws InvalidCustomerNameException, InvalidCustomerIdException, InvalidCustomerEmailException {
        Customer customer = null;
        if (!CustomerNameValidator.isValidName(customerName))
            throw new InvalidCustomerNameException(customerName + " is invalid");
        else if (!CustomerIdValidator.isValidId(customerId))
            throw new InvalidCustomerIdException(customerId + " is invalid");
        else if (!CustomerEmailIdValidator.isValidEmail(email))
            throw new InvalidCustomerEmailException(email + " is not valid email");
        else
            customer = new Customer(customerId, customerName, email);
        return customer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}