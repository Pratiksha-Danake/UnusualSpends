package com.amaap.unusualspends.repository.db;

import com.amaap.unusualspends.domain.model.entity.CreditCard;
import com.amaap.unusualspends.domain.model.entity.Customer;
import com.amaap.unusualspends.domain.model.entity.Transaction;
import com.amaap.unusualspends.repository.db.exception.CustomerAlreadyExistsException;

import java.util.List;

public interface InMemoryDatabase {
    Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;

    CreditCard addCreditCard(CreditCard creditCardToAdd);

    Transaction addTransaction(Transaction transactionToAdd);

    Customer findCustomerBy(int customerId);

    CreditCard getCreditCardBy(long id);

    List<Transaction> getTransactionBy(long id);
}
