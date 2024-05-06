package com.amaap.unusualspends.builder;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;

public class CreditCardBuilder {

    public static CreditCard getCreditCard() throws InvalidCustomerException, InvalidCreditCardIdException {
        Customer customer = Customer.create(1, "Abcd Wxyz", "abc@gmail.com");
        return CreditCard.create(1, customer);
    }
}
