package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailSenderTest {
    private EmailSender emailSender;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        emailSender = injector.getInstance(EmailSender.class);
    }

    @Test
    void shouldThrowInvalidEmailSubjectExceptionIfEmailSubjectIsNull() {
        // arrange
        String subject = null;
        String body = "sample body content";
        String email = "pratikshadanake2001@gmail.com";

        // act && assert
        assertThrows(InvalidEmailSubjectException.class,()->{
            emailSender.sendEmail(subject,body,email);
        });
    }

    @Test
    void shouldThrowInvalidEmailSubjectExceptionIfEmailSubjectIsEmpty() {
        // arrange
        String subject = "";
        String body = "sample body content";
        String email = "pratikshadanake2001@gmail.com";

        // act && assert
        assertThrows(InvalidEmailSubjectException.class,()->{
            emailSender.sendEmail(subject,body,email);
        });
    }
}