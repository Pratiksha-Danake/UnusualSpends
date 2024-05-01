package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.service.CreditCardService;
import com.google.inject.Inject;

public class CreditCardController {
    private CreditCardService creditCardService;

    @Inject
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response createCreditCardFor(Customer customer) throws InvalidCreditCardIdException {
        if (creditCardService.createCreditCardFor(customer) != null)
            return new Response(HttpStatus.OK, "Card Created");
        return new Response(HttpStatus.ERROR_OCCURED, "Error while creating card for customer");
    }
}
