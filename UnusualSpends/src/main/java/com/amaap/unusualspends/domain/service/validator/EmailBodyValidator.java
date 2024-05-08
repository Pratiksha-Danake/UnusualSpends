package com.amaap.unusualspends.domain.service.validator;

public class EmailBodyValidator {
    public static boolean isValidEmailBody(String body) {
        return body != null && !body.isEmpty();
    }
}
