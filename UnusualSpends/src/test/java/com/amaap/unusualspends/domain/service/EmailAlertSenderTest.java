package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.amaap.unusualspends.service.EmailAlertSender;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailAlertSenderTest {
    private EmailAlertSender emailAlertSender;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        emailAlertSender = injector.getInstance(EmailAlertSender.class);
    }

    @Test
    void shouldThrowInvalidEmailSubjectExceptionIfEmailSubjectIsNull() {
        // arrange
        String subject = null;
        String body = "sample body content";
        String email = "pratikshadanake2001@gmail.com";

        // act && assert
        assertThrows(InvalidEmailSubjectException.class, () -> {
            emailAlertSender.sendEmailToCustomer(subject, body, email);
        });
    }

    @Test
    void shouldThrowInvalidEmailSubjectExceptionIfEmailSubjectIsEmpty() {
        // arrange
        String subject = "";
        String body = "sample body content";
        String email = "pratikshadanake2001@gmail.com";

        // act && assert
        assertThrows(InvalidEmailSubjectException.class, () -> {
            emailAlertSender.sendEmailToCustomer(subject, body, email);
        });
    }

    @Test
    void shouldThrowInvalidEmailBodyExceptionIfEmailBodyIsNull() {
        // arrange
        String subject = "sample subject";
        String body = null;
        String email = "pratikshadanake2001@gmail.com";

        // act && assert
        assertThrows(InvalidEmailBodyException.class, () -> {
            emailAlertSender.sendEmailToCustomer(subject, body, email);
        });
    }

    @Test
    void shouldThrowInvalidEmailBodyExceptionIfEmailBodyIsEmpty() {
        // arrange
        String subject = "sample subject";
        String body = "";
        String email = "pratikshadanake2001@gmail.com";

        // act && assert
        assertThrows(InvalidEmailBodyException.class, () -> {
            emailAlertSender.sendEmailToCustomer(subject, body, email);
        });
    }
}