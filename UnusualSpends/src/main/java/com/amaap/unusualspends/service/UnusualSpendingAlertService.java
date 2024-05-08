package com.amaap.unusualspends.service;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
import com.amaap.unusualspends.domain.service.CategoryWiseSpendAnalyzer;
import com.amaap.unusualspends.domain.service.EmailAlertComposer;
import com.amaap.unusualspends.domain.service.dto.SpendDto;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class UnusualSpendingAlertService {
    private final CategoryWiseSpendAnalyzer categoryWiseSpendAnalyzer;
    private final EmailAlertComposer emailAlertComposer;
    private final EmailAlertSender emailAlertSender;
    private final CreditCardService creditCardService;

    @Inject
    public UnusualSpendingAlertService(CategoryWiseSpendAnalyzer categoryWiseSpendAnalyzer, EmailAlertComposer emailAlertComposer, EmailAlertSender emailAlertSender, CreditCardService creditCardService) {
        this.categoryWiseSpendAnalyzer = categoryWiseSpendAnalyzer;
        this.emailAlertComposer = emailAlertComposer;
        this.emailAlertSender = emailAlertSender;
        this.creditCardService = creditCardService;
    }

    public Map<Long, List<SpendDto>> analyzeSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions, double thresholdPercentage) {
        return categoryWiseSpendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
    }

    public boolean sendEmail(Map<Long, List<SpendDto>> spendRecord) {
        for (Map.Entry<Long, List<SpendDto>> entry : spendRecord.entrySet()) {
            long cardId = entry.getKey();
            CreditCard creditCard = creditCardService.getCreditCardBy(cardId);
            Customer customer = creditCard.getCustomer();
            String email = customer.getEmail();
            String name = customer.getName();
            String subject = "Regarding unusual spend for current month";
            List<SpendDto> unusualSpendRecords = entry.getValue();
            String body = emailAlertComposer.composeEmail(name, unusualSpendRecords);
            try {
                emailAlertSender.sendEmailToCustomer(subject, body, email);
            } catch (InvalidCustomerEmailException | InvalidEmailBodyException | InvalidEmailSubjectException e) {
                return false;
            }
        }
        return true;
    }
}
