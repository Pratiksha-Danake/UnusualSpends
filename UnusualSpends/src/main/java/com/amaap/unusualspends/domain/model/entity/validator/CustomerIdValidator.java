package com.amaap.unusualspends.domain.model.entity.validator;

public class CustomerIdValidator {
    public static boolean isValidId(int customerId) {
        if (customerId <= 0)
            return false;
        return true;
    }
}
