package com.amaap.unusualspends.domain.model.entity.exception;

public class InvalidTransactionCategory extends Throwable {
    public InvalidTransactionCategory(String message) {
        super(message);
    }
}
