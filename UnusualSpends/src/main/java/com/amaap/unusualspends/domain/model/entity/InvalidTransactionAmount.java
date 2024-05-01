package com.amaap.unusualspends.domain.model.entity;

public class InvalidTransactionAmount extends Throwable {
    public InvalidTransactionAmount(String message) {
        super(message);
    }
}
