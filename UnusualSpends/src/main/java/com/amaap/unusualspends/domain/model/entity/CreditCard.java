package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.validator.CreditCardIdValidator;

import java.util.Objects;

public class CreditCard {
    private final long id;
    private final Customer customer;

    private CreditCard(long id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static CreditCard create(long cardId, Customer customer) throws InvalidCreditCardIdException {
        if (!CreditCardIdValidator.isValidId(cardId))
            throw new InvalidCreditCardIdException("Id is not valid");
        return new CreditCard(cardId, customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
