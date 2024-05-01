package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.CreditCard;

public interface CreditCardRepository {
    public CreditCard addCreditCard(CreditCard creditCardToAdd);
}
