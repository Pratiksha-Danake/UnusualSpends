package com.amaap.unusualspends.domain.model.entity.validator;

import java.util.regex.Pattern;

public class CustomerEmailIdValidator {
    public static boolean isValidEmail(String customerEmail) {
        return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", customerEmail);
    }
}
