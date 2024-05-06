package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.builder.CreditCardBuilder;
import com.amaap.unusualspends.builder.UnusualSpendCustomerBuilder;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.TransactionService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpendAnalyzerTest {
    CreditCardService creditCardService;
    TransactionService transactionService;
    SpendAnalyzer spendAnalyzer;

    @BeforeEach()
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
        spendAnalyzer = injector.getInstance(SpendAnalyzer.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpendFromTransactionsData() throws InvalidTransactionCategory, InvalidTransactionAmountException, InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Map<Long, List<SpendsDto>> expectedCustomers = UnusualSpendCustomerBuilder.getUnusualSpendCustomers();
        double thresholdPercentage = 20;

        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;

        // act
        CreditCard creditCard = CreditCardBuilder.getCreditCard();

        transactionService.createTransaction(creditCard.getId(), Category.GROCERIES, 400, LocalDate.of(currentYear, currentMonth, 20));
        transactionService.createTransaction(creditCard.getId(), Category.TRAVEL, 600, LocalDate.of(currentYear, currentMonth, 22));
        transactionService.createTransaction(creditCard.getId(), Category.GROCERIES, 100, LocalDate.of(prevYear, prevMonth, 23));
        transactionService.createTransaction(creditCard.getId(), Category.TRAVEL, 200, LocalDate.of(prevYear, prevMonth, 22));

        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(prevMonth);
        Map<Long, List<SpendsDto>> actualCustomers = spendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expectedCustomers, actualCustomers);
    }
}
