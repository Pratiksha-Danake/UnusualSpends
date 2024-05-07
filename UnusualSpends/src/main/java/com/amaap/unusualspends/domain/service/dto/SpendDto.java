package com.amaap.unusualspends.domain.service.dto;

import com.amaap.unusualspends.domain.model.valueobject.Category;

import java.util.Objects;

public class SpendDto {
    Category category;
    double unusualAmountSpend;
    double usualAmountSpend;

    public SpendDto(Category category, double usualAmountSpend, double UnusualAmountSpend) {
        this.category = category;
        this.usualAmountSpend = usualAmountSpend;
        this.unusualAmountSpend = UnusualAmountSpend;
    }

    public Category getCategory() {
        return category;
    }

    public double getUnusualAmountSpend() {
        return unusualAmountSpend;
    }

    public double getUsualAmountSpend() {
        return usualAmountSpend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpendDto spendsDto = (SpendDto) o;
        return Double.compare(spendsDto.unusualAmountSpend, unusualAmountSpend) == 0 && Double.compare(spendsDto.usualAmountSpend, usualAmountSpend) == 0 && category == spendsDto.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, unusualAmountSpend, usualAmountSpend);
    }
}
