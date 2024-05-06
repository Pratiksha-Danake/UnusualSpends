package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.builder.CreditCardBuilder;
import com.amaap.unusualspends.builder.UnusualSpendCustomerBuilder;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.amaap.unusualspends.service.CreditCardCompanyService;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardCompanyControllerTest {
    TransactionService transactionService;
    CustomerService customerService;

    CreditCardService creditCardService;

    CreditCardCompanyController creditCardCompanyController;

    @BeforeEach
    void setUp(){
        Injector injector = Guice.createInjector(new AppModule());
        transactionService = injector.getInstance(TransactionService.class);
        customerService = injector.getInstance(CustomerService.class);
        creditCardService = injector.getInstance(CreditCardService.class);
        creditCardCompanyController = injector.getInstance(CreditCardCompanyController.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpendFromGivenTransactionData() throws InvalidCreditCardIdException, InvalidCustomerException, InvalidTransactionCategory, InvalidTransactionAmount {
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
        Map<Long, List<SpendsDto>> actualCustomers = creditCardCompanyController.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    void shouldBeAbleToSendEmailRegardingUnusualSpendInCurrentMonth() throws InvalidCustomerException, CustomerAlreadyExistsException, InvalidCreditCardIdException, InvalidTransactionCategory, InvalidTransactionAmount {
        // arrange
        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;

        // act
        Customer customer = customerService.createCustomerToAdd("Pratiksha Danake", "pratikshadanake2001@gmial.com");
        creditCardService.createCreditCardFor(customer);

        transactionService.createTransaction(1, Category.GROCERIES, 400, LocalDate.of(currentYear, currentMonth, 10));
        transactionService.createTransaction(1, Category.TRAVEL, 600, LocalDate.of(currentYear, currentMonth, 12));
        transactionService.createTransaction(1, Category.GROCERIES, 100, LocalDate.of(prevYear, prevMonth, 20));
        transactionService.createTransaction(1, Category.TRAVEL, 200, LocalDate.of(prevYear, prevMonth, 13));

        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(prevMonth);

        Map<Long, List<SpendsDto>> spendRecord = creditCardCompanyController.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
        boolean isSent = creditCardCompanyController.sendEmail(spendRecord);

        // assert
        assertTrue(isSent);
    }
}
