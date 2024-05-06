package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidTransactionAmountException extends Throwable {
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}
