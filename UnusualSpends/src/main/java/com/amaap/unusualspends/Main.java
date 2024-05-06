package com.amaap.unusualspends;

import com.amaap.unusualspends.controller.CreditCardCompanyController;
import com.amaap.unusualspends.controller.dto.Response;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCreditCardIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionAmountException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidTransactionCategory;
import com.amaap.unusualspends.domain.model.valueobject.Category;
import com.amaap.unusualspends.domain.service.dto.SpendsDto;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;
import com.amaap.unusualspends.service.CreditCardService;
import com.amaap.unusualspends.service.CustomerService;
import com.amaap.unusualspends.service.TransactionService;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InvalidCustomerException, CustomerAlreadyExistsException, InvalidCreditCardIdException, InvalidTransactionCategory, InvalidTransactionAmountException {
        Injector injector = Guice.createInjector(new AppModule());
        CustomerService customerService = injector.getInstance(CustomerService.class);
        CreditCardService creditCardService = injector.getInstance(CreditCardService.class);
        TransactionService transactionService = injector.getInstance(TransactionService.class);
        CreditCardCompanyController creditCardCompanyController = injector.getInstance(CreditCardCompanyController.class);

        double thresholdPercentage = 20;
        Month currentMonth = LocalDate.now().getMonth();
        Month prevMonth = currentMonth.minus(1);
        int currentYear = LocalDate.now().getYear();
        int prevYear = currentMonth == Month.JANUARY ? currentYear - 1 : currentYear;

        // act
        Customer customer = customerService.createCustomerToAdd("Pratiksha Danake", "pratikshadanake2001@gmail.com");
        creditCardService.createCreditCardFor(customer);
        transactionService.createTransaction(1, Category.GROCERIES, 400, LocalDate.of(currentYear, currentMonth, 10));
        transactionService.createTransaction(1, Category.TRAVEL, 600, LocalDate.of(currentYear, currentMonth, 12));
        transactionService.createTransaction(1, Category.GROCERIES, 100, LocalDate.of(prevYear, prevMonth, 20));
        transactionService.createTransaction(1, Category.TRAVEL, 200, LocalDate.of(prevYear, prevMonth, 13));

        List<Transaction> currentMonthTransactions = transactionService.getTransactionsByMonth(currentMonth);
        List<Transaction> previousMonthTransactions = transactionService.getTransactionsByMonth(prevMonth);

        Map<Long, List<SpendsDto>> spendRecord = creditCardCompanyController.analyzeSpend(currentMonthTransactions, previousMonthTransactions, thresholdPercentage);
        Response response = creditCardCompanyController.sendEmail(spendRecord);
        System.out.println(response.getMessage());
    }
}
