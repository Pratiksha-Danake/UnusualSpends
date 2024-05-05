package com.amaap.unusualspends.repository;

import com.amaap.unusualspends.domain.model.entity.CreditCard;

public interface CreditCardRepository {
    CreditCard addCreditCard(CreditCard creditCardToAdd);

    CreditCard getCreditCardBy(long id);
}
