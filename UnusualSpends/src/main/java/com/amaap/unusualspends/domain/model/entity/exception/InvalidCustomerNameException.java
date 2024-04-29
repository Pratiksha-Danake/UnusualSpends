package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidCustomerNameException extends InvalidCustomerException {
    public InvalidCustomerNameException(String name) {
        super(name);
    }
}
