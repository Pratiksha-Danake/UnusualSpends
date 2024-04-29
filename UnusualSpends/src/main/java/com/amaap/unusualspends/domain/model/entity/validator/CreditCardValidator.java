package com.amaap.unusualspends.domain.model.entity.validator;

public class CreditCardValidator {

    public static boolean isValidId(long cardId) {
        if (cardId <= 0)
            return false;
        return true;
    }
}
