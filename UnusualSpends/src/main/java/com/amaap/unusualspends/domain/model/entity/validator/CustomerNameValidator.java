package com.amaap.unusualspends.domain.model.entity.validator;

public class CustomerNameValidator {
    public static boolean isValidName(String customerName) {
        if (customerName == null || customerName.isEmpty() || !(customerName.matches("^([A-Za-z]{4,} [A-Za-z]{3,})$")))
            return false;
        return true;
    }
}
