package com.amaap.unusualspends.domain.model.entity.validator;

public class CreditCardIdValidator {
    public static boolean isValidId(long cardId) {
        return cardId > 0;
    }
}
