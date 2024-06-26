package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.amaap.unusualspends.domain.model.entity.validator.CustomerEmailIdValidator.isValidEmail;
import static com.amaap.unusualspends.domain.service.validator.EmailBodyValidator.isValidEmailBody;
import static com.amaap.unusualspends.domain.service.validator.EmailSubjectValidator.isValidEmailSubject;

public class EmailAlertSender {
    public void sendEmailToCustomer(String subject, String body, String email) throws InvalidCustomerEmailException, InvalidEmailBodyException, InvalidEmailSubjectException {
        if (!isValidEmail(email))
            throw new InvalidCustomerEmailException(email);
        if (!isValidEmailBody(body))
            throw new InvalidEmailBodyException("Email body should not be empty..!");
        if (!isValidEmailSubject(subject))
            throw new InvalidEmailSubjectException("Email without subject..!");

        String fromEmail = "danakepratiksha1020@gmail.com";

        String toEmail = email;
        String password = "jdca jiof oufs exwz";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
