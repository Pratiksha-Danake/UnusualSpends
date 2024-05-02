package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.valueobject.Category;

public class TransactionValidator {
    public static boolean isValidCategory(Category category) {
        return category != null;
    }

    public static boolean isValidSpend(Double spend) {
        return spend > 0;
    }
}
