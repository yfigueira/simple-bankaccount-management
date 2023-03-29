package org.example.account;

import org.example.money.Money;
import org.example.transaction.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountLimitTest {

    private AccountLimit limits;

    @BeforeEach
    void setUp() {
        limits = new AccountLimit();
    }

    @AfterEach
    void tearDown() {
        limits = null;
    }

    @Test
    void whenInitialized_ShouldHaveLimitEqualToOneThousand() {
        // given field limits and:
        Money oneThousand = new Money(new BigDecimal("1000.00"));
        // then
        assertThat(limits.getTransactionLimit(), is(equalTo(oneThousand)));
    }

    @Test
    void settingLimitToTwoThousand_ShouldChangeLimitToTwoThousand() {
        // given field limits and:
        Money twoThousand = new Money(new BigDecimal("2000.00"));
        // when
        limits.setTransactionLimit(twoThousand);
        // then
        assertThat(limits.getTransactionLimit(), is(equalTo(twoThousand)));
    }

    @Test
    void settingLimitToLessThanZero_ShouldThrowUnauthorizedBankOperationException() {
        // given field limits and:
        Money minusOne = new Money(new BigDecimal("-1.00"));
        // then
        assertThrows(UnauthorizedBankOperationException.class, () -> limits.setTransactionLimit(minusOne));
    }

    @Test
    void validatingTransactionWithAmountOverLimit_ShouldThrowAccountLimitsExceededException() {
        // given field limits and:
        Money initialBalance = new Money(new BigDecimal("3000.00"));
        Money moreThanOneThousand = new Money(new BigDecimal("1001.00"));
        // when
        Transaction deposit = Transaction.deposit(initialBalance, moreThanOneThousand);
        Transaction withdrawal = Transaction.withdrawal(initialBalance, moreThanOneThousand);
        // then
        assertThrows(AccountLimitExceededException.class, () -> limits.validate(deposit));
        assertThrows(AccountLimitExceededException.class, () -> limits.validate(withdrawal));
    }

    @Test
    void validatingTransactionWithAmountWithinLimit_ShouldNotThrowAccountLimitsExceededException() {
        // given field limits and:
        Money initialBalance = new Money(new BigDecimal("3000.00"));
        Money lessThanOneThousand = new Money(new BigDecimal("999.00"));
        // when
        Transaction deposit = Transaction.deposit(initialBalance, lessThanOneThousand);
        Transaction withdrawal = Transaction.withdrawal(initialBalance, lessThanOneThousand);
        // then
        assertDoesNotThrow(() -> limits.validate(deposit));
        assertDoesNotThrow(() -> limits.validate(withdrawal));
    }
}