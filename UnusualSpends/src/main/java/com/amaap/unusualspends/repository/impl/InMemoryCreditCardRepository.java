package com.amaap.unusualspends.repository.impl;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.google.inject.Inject;

public class InMemoryCreditCardRepository implements CreditCardRepository {
    private final InMemoryDatabase inMemoryDatabase;

    @Inject
    public InMemoryCreditCardRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    public CreditCard addCreditCard(CreditCard creditCardToAdd) {
        return inMemoryDatabase.addCreditCard(creditCardToAdd);
    }
}
