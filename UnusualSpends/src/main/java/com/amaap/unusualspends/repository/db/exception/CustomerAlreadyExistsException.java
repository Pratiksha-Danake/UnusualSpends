package com.amaap.unusualspends.repository.db.exception;

public class CustomerAlreadyExistsException extends Throwable {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
