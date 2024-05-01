package com.amaap.unusualspends;

import com.amaap.unusualspends.repository.CreditCardRepository;
import com.amaap.unusualspends.repository.CustomerRepository;
import com.amaap.unusualspends.repository.db.InMemoryDatabase;
import com.amaap.unusualspends.repository.db.impl.FakeInMemoryDatabase;
import com.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import com.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import com.amaap.unusualspends.service.CustomerService;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CreditCardRepository.class).to(InMemoryCreditCardRepository.class);
        bind(CustomerRepository.class).to(InMemoryCustomerRepository.class);
        bind(InMemoryDatabase.class).to(FakeInMemoryDatabase.class).asEagerSingleton();
    }
}