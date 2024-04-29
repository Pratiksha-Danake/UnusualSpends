package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.validator.CreditCardValidator;

import java.util.Objects;

public class CreditCard {
    private long id;

    public CreditCard(long id) {
        this.id = id;
    }

    public static CreditCard create(long cardId) throws InvalidCreditCardIdException {
        if(!CreditCardValidator.isValidId(cardId))
            throw new InvalidCreditCardIdException("Id is not valid");
        return new CreditCard(cardId);
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
