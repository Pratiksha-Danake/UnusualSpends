package com.amaap.unusualspends.domain.model.entity.validator;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;

import java.util.regex.Pattern;

public class CustomerEmailIdValidator {
    public static boolean isValidEmail(String customerEmail) throws InvalidCustomerEmailException {
        if ((Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", customerEmail)))
            return true;
        else
            return false;
    }
}