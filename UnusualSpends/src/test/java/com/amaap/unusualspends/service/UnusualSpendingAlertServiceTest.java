package com.amaap.unusualspends.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.builder.CreditCardBuilder;
import com.amaap.unusualspends.builder.UnusualSpendCustomerBuilder;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategoryException;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendDto;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
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

public class UnusualSpendingAlertServiceTest {
    CustomerService customerService;
    CreditCardService creditCardService;
    TransactionService transactionService;
    UnusualSpendingAlertService unusualSpendingAlertService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
        unusualSpendingAlertService = injector.getInstance(UnusualSpendingAlertService.class);
        customerService = injector.getInstance(CustomerService.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpendFromGivenTransactionData() throws InvalidCreditCardIdException, InvalidCustomerException, InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        setUpTransactions();

        // act
        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth());
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth().minus(1));
        Map<Long, List<SpendDto>> actualCustomers = unusualSpendingAlertService.analyzeSpend(currentMonthTransactions, previousMonthTransactions, 20);

        // assert
        assertEquals(UnusualSpendCustomerBuilder.getUnusualSpendCustomers(), actualCustomers);
    }

    @Test
    void shouldBeAbleToSendEmailRegardingUnusualSpendInCurrentMonth() throws InvalidCustomerException, CustomerAlreadyExistsException, InvalidCreditCardIdException, InvalidTransactionCategoryException, InvalidTransactionAmountException {
        // arrange
        Customer customer = customerService.createCustomerToAdd("Pratiksha Danake", "pratikshadanake2001@gmial.com");
        creditCardService.createCreditCardFor(customer);
        setUpTransactions();

        // act
        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth());
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(LocalDate.now().getMonth().minus(1));
        Map<Long, List<SpendDto>> spendRecord = unusualSpendingAlertService.analyzeSpend(currentMonthTransactions, previousMonthTransactions, 20);
        boolean isSent = unusualSpendingAlertService.sendEmail(spendRecord);

        // assert
        assertTrue(isSent);
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

