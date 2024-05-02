package com.amaap.unusualspends.domain.model.entity;

import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerEmailException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerIdException;
import com.amaap.unusualspends.domain.model.entity.exception.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerTest {
    @Test
    void shouldThrowInvalidCustomerNameExceptionWhenEmptyCustomerNamePassedIsEmpty() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "", "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldThrowInvalidCustomerNameExceptionWhenNullCustomerNamePassedIsNull() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, null, "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldThrowInvalidCustomerNameExceptionWhenCustomerNamePassedIsNotInCorrectFormat() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Ba          ", "sita@gmail.com");
        }, "");
    }

    @Test
    void shouldThrowInvalidCustomerIdExceptionWhenCustomerIdPassedIsZero() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(0, "sita ram", "sita@gmail.com");
        }, "0");
    }

    @Test
    void shouldThrowInvalidCustomerIdExceptionWhenCustomerIdPassedIsNegative() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(-1, "sita ram", "sita@gmail.com");
        }, "-1");
    }

    @Test
    void shouldThrowInvalidCustomerEmailExceptionWhenCustomerEmailPassedIsInvalid() {
        // act && assert
        Assertions.assertThrows(InvalidCustomerEmailException.class, () -> {
            Customer.create(1, "sita ram", "invalid_email@example");
        });
    }

    @Test
    void shouldBeAbleToReturnSameHashcodeForEqualInstancesOfCustomerClass() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // arrange && act
        int firstCustomerHashCode = Customer.create(2, "sita kadam", "sai@gmail.com").hashCode();
        int secondCustomerHashCode = Customer.create(2, "sita kadam", "sai@gmail.com").hashCode();
        // assert
        assertEquals(firstCustomerHashCode, secondCustomerHashCode);
    }

    @Test
    public void shouldBeReturnTrueWhenComparisonIsBetweenSameInstance() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // arrange
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(2, "John Does", "john@example.com");

        // act && assert
        assertEquals(customer1, customer1);
        assertNotEquals(null, customer1);
        assertNotEquals("Other", customer1);
    }

    @Test
    public void shouldBeAbleToReturnFalseWhenCustomerIdForTwoInstanceIsDifferent() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(2, "John Does", "john@example.com");
        assertNotEquals(customer1, customer2);
    }

    @Test
    public void shouldBeAbleToReturnFalseWhenCustomerNameForTwoInstanceIsDifferent() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(1, "John Doe", "john@example.com");
        assertNotEquals(customer1, customer2);
    }

    @Test
    public void shouldBeAbleToReturnFalseWhenCustomerEmailForTwoInstanceIsDifferent() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer1 = Customer.create(1, "John Does", "john@example.com");
        Customer customer2 = Customer.create(1, "John Does", "jane@example.com");
        assertNotEquals(customer1, customer2);
    }
}
