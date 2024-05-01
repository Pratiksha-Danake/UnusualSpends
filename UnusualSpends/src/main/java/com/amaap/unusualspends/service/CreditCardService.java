package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.repository.CreditCardRepository;
import com.google.inject.Inject;

public class CreditCardService {
    private CreditCardRepository creditCardRepository;
    private long creditCardId = 0;

    @Inject
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard createCreditCardFor(Customer customer) throws InvalidCreditCardIdException {
        creditCardId++;
        CreditCard creditCard = CreditCard.create(creditCardId,customer);
        return creditCardRepository.addCreditCard(creditCard);
    }
}
