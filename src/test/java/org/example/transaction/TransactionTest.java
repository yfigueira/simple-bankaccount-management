package org.example.transaction;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {

    @Test
    void whenInitialized_ShouldHaveInitialBalanceAndAmountAndType() {
        // given
        Money initialBalance = new Money(new BigDecimal("1000.00"));
        Money amount = new Money(new BigDecimal("100.00"));
        // when
        Transaction transaction = Transaction.deposit(initialBalance, amount);
        // then
        assertThat(transaction.getInitialBalance(), is(not(nullValue())));
        assertThat(transaction.getAmount(), is(not(nullValue())));
        assertThat(transaction.getType(), is(not(nullValue())));
    }

    @Test
    void whenInitialized_ShouldNotHaveDate() {
        // given
        Money initialBalance = new Money(new BigDecimal("1000.00"));
        Money amount = new Money(new BigDecimal("100.00"));
        // when
        Transaction transaction = Transaction.deposit(initialBalance, amount);
        // then
        assertThat(transaction.getDate(), is(nullValue()));
    }

    @Test
    void requestingTransactionWithNegativeAmount_ShouldThrowInvalidTransactionRequestException() {
        // given
        Money balance = new Money(new BigDecimal("1000.00"));
        Money negativeAmount = new Money(new BigDecimal("-100.00"));
        // then
        assertThrows(InvalidTransactionRequestException.class, () -> Transaction.deposit(balance, negativeAmount).run());
        assertThrows(InvalidTransactionRequestException.class, () -> Transaction.withdrawal(balance, negativeAmount).run());
    }

    @Test
    void requestingWithdrawalWithAmountHigherThanBalance_ShouldThrowInsufficientFundsException() {
        // given
        Money balance = new Money(new BigDecimal("100.00"));
        Money amount = new Money(new BigDecimal("101.00"));
        // then
        assertThrows(InvalidTransactionRequestException.class, () -> Transaction.withdrawal(balance, amount).run());
    }
}
