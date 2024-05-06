package com.amaap.unusualspends.domain.service.dto;

import java.util.Objects;

public class UnusualSpendCustomer {
    long creditCardId;
    SpendsDto spendsDto;

    public UnusualSpendCustomer(long cardId, SpendsDto spendsDto) {
        this.creditCardId = cardId;
        this.spendsDto = spendsDto;
    }

    public long getCreditCardId() {
        return creditCardId;
    }

    public SpendsDto getSpendsDto() {
        return spendsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnusualSpendCustomer customer = (UnusualSpendCustomer) o;
        return creditCardId == customer.creditCardId && Objects.equals(spendsDto, customer.spendsDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardId, spendsDto);
    }
}
