package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
import com.amaap.unusualspends.domain.service.EmailSender;
import com.amaap.unusualspends.domain.service.SpendAnalyzer;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class CreditCardCompanyService {
    private final SpendAnalyzer spendAnalyzer;
    private final EmailComposer emailComposer;
    private final EmailSender emailSender;
    private final CreditCardService creditCardService;

    @Inject
    public CreditCardCompanyService(SpendAnalyzer spendAnalyzer, EmailComposer emailComposer, EmailSender emailSender, CreditCardService creditCardService) {
        this.spendAnalyzer = spendAnalyzer;
        this.emailComposer = emailComposer;
        this.emailSender = emailSender;
        this.creditCardService = creditCardService;
    }

    public Map<Long, List<SpendsDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return spendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
    }

    public boolean sendEmail(Map<Long, List<SpendsDto>> spendRecord) {
        try {
            for (Map.Entry<Long, List<SpendsDto>> entry : spendRecord.entrySet()) {
                long cardId = entry.getKey();
                CreditCard creditCard = creditCardService.getCreditCardBy(cardId);
                String email = creditCard.getCustomer().getEmail();
                String name = creditCard.getCustomer().getName();
                String subject = "Regarding unusual spend for current month";
                List<SpendsDto> unusualSpendRecords = entry.getValue();
                String body = emailComposer.composeEmail(name, unusualSpendRecords);
                System.out.println(body);
                emailSender.sendEmail(subject, body, email);
            }
        } catch (InvalidCustomerEmailException | InvalidEmailBodyException | InvalidEmailSubjectException e) {
            return false;
        }
        return true;
    }
}
