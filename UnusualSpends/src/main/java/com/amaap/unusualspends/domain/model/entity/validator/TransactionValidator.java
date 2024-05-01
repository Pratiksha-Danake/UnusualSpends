package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.valueobject.Category;

public class TransactionValidator {
    public static boolean isValidCategory(Category category) {
        if (category != null)
            return true;
        return false;
    }

    public static boolean isValidSpend(int spend) {
        if (spend >= 0)
            return true;
        return false;
    }
}
