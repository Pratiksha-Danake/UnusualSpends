package com.amaap.unusualspends.domain.service.validator;

public class EmailSubjectValidator {
    public static boolean isValidEmailSubject(String subject) {
        return subject != null && !subject.isEmpty();
    }
}
