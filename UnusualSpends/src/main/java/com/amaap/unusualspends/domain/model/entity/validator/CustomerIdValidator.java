package com.amaap.unusualspends.domain.model.entity.validator;

public class CustomerIdValidator {
    public static boolean isValidId(int customerId) {
        return customerId > 0;
    }
}
