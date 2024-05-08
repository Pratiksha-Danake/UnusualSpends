package com.amaap.unusualspends.domain.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.builder.CreditCardBuilder;
import com.amaap.unusualspends.builder.UnusualSpendCustomerBuilder;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendDto;
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

public class CategoryWiseSpendAnalyzerTest {
    CreditCardService creditCardService;
    TransactionService transactionService;
    CategoryWiseSpendAnalyzer categoryWiseSpendAnalyzer;

    @BeforeEach()
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
        categoryWiseSpendAnalyzer = injector.getInstance(CategoryWiseSpendAnalyzer.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpendFromTransactionsData() throws InvalidTransactionCategoryException, InvalidTransactionAmountException, InvalidCustomerException, InvalidCreditCardIdException {
        // arrange
        Map<Long, List<SpendDto>> expectedCustomers = UnusualSpendCustomerBuilder.getUnusualSpendCustomers();
        double thresholdPercentage = 24;

        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;

        // act
        CreditCard creditCard = CreditCardBuilder.getCreditCard();


        transactionService.createTransaction(creditCard.getId(), Category.SHOPPING, 500, LocalDate.of(currentYear, currentMonth, 13));
        transactionService.createTransaction(creditCard.getId(), Category.TRAVEL, 900, LocalDate.of(currentYear, currentMonth, 24));
        transactionService.createTransaction(creditCard.getId(), Category.SHOPPING, 200, LocalDate.of(prevYear, prevMonth, 24));
        transactionService.createTransaction(creditCard.getId(), Category.TRAVEL, 500, LocalDate.of(prevYear, prevMonth, 13));

        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(prevMonth);
        Map<Long, List<SpendDto>> actualCustomers = categoryWiseSpendAnalyzer.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expectedCustomers, actualCustomers);
    }
}
