package com.amaap.unusualspends.domain.service.dto;

import com.amaap.unusualspends.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpendDtoTest {
    @Test
    public void shouldBeAbleToReturnSameHashCodeForTwoEqualInstances() {
        // arrange
        Category category = Category.SHOPPING;
        double unusualAmountSpend = 500.0;
        double usualAmountSpend = 200.0;
        SpendDto spendDto1 = new SpendDto(category, unusualAmountSpend, usualAmountSpend);
        SpendDto spendDto2 = new SpendDto(category, unusualAmountSpend, usualAmountSpend);

        // act
        int hashCode1 = spendDto1.hashCode();
        int hashCode2 = spendDto2.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void shouldBeAbleToTestEqualityAndUnEqualityOfTwoDifferentInstancesAccordingEqualsAndHashCodeContract() {
        // arrange
        SpendDto spendDto1 = new SpendDto(Category.SHOPPING, 500.0, 200.0);
        SpendDto spendDto2 = new SpendDto(Category.SHOPPING, 500.0, 200.0);
        SpendDto spendDto3 = new SpendDto(Category.TRAVEL, 500.0, 200.0);
        SpendDto spendDto4 = new SpendDto(Category.SHOPPING, 600.0, 200.0);
        SpendDto spendDto5 = new SpendDto(Category.SHOPPING, 500.0, 300.0);

        // act && assert
        assertAll(
                () -> assertTrue(spendDto1.equals(spendDto1)),
                () -> assertTrue(spendDto1.equals(spendDto2)),
                () -> assertTrue(spendDto2.equals(spendDto1)),

                () -> assertFalse(spendDto1.equals(null)),
                () -> assertFalse(spendDto1.equals("SpendDto")),

                () -> assertFalse(spendDto1.equals(spendDto3)),
                () -> assertFalse(spendDto3.equals(spendDto1)),

                () -> assertFalse(spendDto1.equals(spendDto4)),
                () -> assertFalse(spendDto4.equals(spendDto1)),

                () -> assertFalse(spendDto1.equals(spendDto5)),
                () -> assertFalse(spendDto5.equals(spendDto1))
        );
    }
}