package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.service.CreditCardService;
import com.google.inject.Inject;

public class CreditCardController {
    private final CreditCardService creditCardService;

    @Inject
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response createCreditCardFor(Customer customer) {
        Response responseToSend = null;
        try {
            if (creditCardService.createCreditCardFor(customer) != null)
                responseToSend = new Response(HttpStatus.OK, "Card Created");
        } catch (InvalidCreditCardIdException e) {
            responseToSend = new Response(HttpStatus.ERROR_OCCURED, "Error while creating card for customer");
        }
        return responseToSend;
    }

    public CreditCard getCreditCardBy(long id) {
        return creditCardService.getCreditCardBy(id);
    }
}
