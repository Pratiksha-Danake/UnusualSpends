package com.amaap.unusualspends.controller;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.builder.CreditCardBuilder;
import com.amaap.unusualspends.builder.UnusualSpendCustomerBuilder;
import com.amaap.unusualspends.controller.dto.HttpStatus;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendDto;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailBodyException;
import com.amaap.unusualspends.domain.service.exception.InvalidEmailSubjectException;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
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

public class UnusualSpendingAlertControllerTest {
    TransactionService transactionService;
    CustomerService customerService;
    CreditCardService creditCardService;
    UnusualSpendingAlertController unusualSpendingAlertController;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        transactionService = injector.getInstance(TransactionService.class);
        customerService = injector.getInstance(CustomerService.class);
        creditCardService = injector.getInstance(CreditCardService.class);
        unusualSpendingAlertController = injector.getInstance(UnusualSpendingAlertController.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpendFromGivenTransactionData() throws InvalidCreditCardIdException, InvalidCustomerException, InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Map<Long, List<SpendDto>> expectedCustomers = UnusualSpendCustomerBuilder.getUnusualSpendCustomers();
        double thresholdPercentage = 30;

        // act
        setUpTransactions();

        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth());
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth().minus(1));
        Map<Long, List<SpendDto>> actualCustomers = unusualSpendingAlertController.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    void shouldBeAbleToSendEmailRegardingUnusualSpendInCurrentMonth() throws InvalidCustomerException, CustomerAlreadyExistsException, InvalidCreditCardIdException, InvalidTransactionAmountException, InvalidTransactionCategoryException, InvalidEmailSubjectException, InvalidEmailBodyException {
        // arrange
        double thresholdPercentage = 20;
        Response expected = new Response(HttpStatus.OK, "Email Sent");

        // act
        setUpTransactions();

        Customer customer = customerService.createCustomerToAdd("Pratiksha Danake", "pratikshadanake2001@gmial.com");
        creditCardService.createCreditCardFor(customer);

        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth());
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth().minus(1));

        Map<Long, List<SpendDto>> spendRecord = unusualSpendingAlertController.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
        Response actual = unusualSpendingAlertController.sendEmail(spendRecord);

        // assert
        assertEquals(expected, actual);
    }

    private void setUpTransactions() throws InvalidCreditCardIdException, InvalidTransactionCategoryException, InvalidTransactionAmountException, InvalidCustomerException {
        int currentYear = LocalDate.now().getYear();
        int prevYear = LocalDate.now().getMonth() == Month.JANUARY ? currentYear - 1 : currentYear;

        CreditCard creditCard = CreditCardBuilder.getCreditCard();

        transactionService.createTransaction(creditCard.getId(), Category.SHOPPING, 500, LocalDate.of(currentYear, LocalDate.now().getMonth(), 13));
        transactionService.createTransaction(creditCard.getId(), Category.TRAVEL, 900, LocalDate.of(currentYear, LocalDate.now().getMonth(), 24));
        transactionService.createTransaction(creditCard.getId(), Category.SHOPPING, 200, LocalDate.of(prevYear, LocalDate.now().getMonth().minus(1), 24));
        transactionService.createTransaction(creditCard.getId(), Category.TRAVEL, 500, LocalDate.of(prevYear, LocalDate.now().getMonth().minus(1), 13));
    }
}

