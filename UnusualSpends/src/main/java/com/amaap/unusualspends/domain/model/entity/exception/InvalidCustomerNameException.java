package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidCustomerNameException extends Throwable {
    public InvalidCustomerNameException(String name) {
        super(name);
    }
}
