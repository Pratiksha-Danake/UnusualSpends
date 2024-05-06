package com.amaap.unusualspends.service;

import com.amaap.unusualspends.AppModule;
import com.amaap.unusualspends.builder.CreditCardBuilder;
import com.amaap.unusualspends.builder.UnusualSpendCustomerBuilder;
import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.InvalidTransactionAmount;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.UnusualSpendCustomer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardCompanyServiceTest {
    CustomerService customerService;
    CreditCardService creditCardService;
    TransactionService transactionService;
    CreditCardCompanyService creditCardCompanyService;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new AppModule());
        creditCardService = injector.getInstance(CreditCardService.class);
        transactionService = injector.getInstance(TransactionService.class);
        creditCardCompanyService = injector.getInstance(CreditCardCompanyService.class);
        customerService = injector.getInstance(CustomerService.class);
    }

    @Test
    void shouldBeAbleToFindUnusualSpendFromGivenTransactionData() throws InvalidCreditCardIdException, InvalidCustomerException, InvalidTransactionCategory, InvalidTransactionAmount {
        // arrange
        List<UnusualSpendCustomer> expectedCustomers = UnusualSpendCustomerBuilder.getUnusualSpendCustomers();
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
        List<UnusualSpendCustomer> actualCustomers = creditCardCompanyService.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);

        // assert
        assertEquals(expectedCustomers, actualCustomers);
    }

//    @Test
//    void shouldBeAbleToSendEmailRegardingUnusualSpendInCurrentMonth(){
//        // arrange
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(1,));
//    }
}
