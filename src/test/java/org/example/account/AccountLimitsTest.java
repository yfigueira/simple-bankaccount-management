package org.example.account;

import org.example.transaction.Money;
import org.example.transaction.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountLimitsTest {

    private AccountLimits limits;

    @BeforeEach
    void setUp() {
        limits = new AccountLimits();
    }

    @AfterEach
    void tearDown() {
        limits = null;
    }

    @Test
    void whenInitialized_ShouldHaveDepositLimitEqualToOneThousand() {
        // given field limits and:
        Money oneThousand = new Money(new BigDecimal("1000.00"));
        // then
        assertThat(limits.getDepositLimit(), is(equalTo(oneThousand)));
    }

    @Test
    void whenInitialized_ShouldHaveWithdrawalLimitEqualToOneThousand() {
        // given field limits and:
        Money oneThousand = new Money(new BigDecimal("1000.00"));
        // then
        assertThat(limits.getWithdrawalLimit(), is(equalTo(oneThousand)));
    }

    @Test
    void settingLimitsToTwoThousand_ShouldChangeLimitsToTwoThousand() {
        // given field limits and:
        Money twoThousand = new Money(new BigDecimal("2000.00"));
        // when
        limits.setDepositLimit(twoThousand);
        limits.setWithdrawalLimit(twoThousand);
        // then
        assertThat(limits.getDepositLimit(), is(equalTo(twoThousand)));
        assertThat(limits.getWithdrawalLimit(), is(equalTo(twoThousand)));
    }

    @Test
    void settingLimitToLessThanZero_ShouldThrowUnauthorizedBankOperationException() {
        // given field limits and:
        Money minusOne = new Money(new BigDecimal("-1.00"));
        // then
        assertThrows(UnauthorizedBankOperationException.class, () -> limits.setWithdrawalLimit(minusOne));
        assertThrows(UnauthorizedBankOperationException.class, () -> limits.setDepositLimit(minusOne));
    }

    @Test
    void validatingTransactionWithAmountOverLimits_ShouldThrowAccountLimitsExceededException() {
        // given field limits and:
        Money initialBalance = new Money(new BigDecimal("3000.00"));
        Money moreThanOneThousand = new Money(new BigDecimal("1001.00"));
        // when
        Transaction deposit = Transaction.deposit(initialBalance, moreThanOneThousand);
        Transaction withdrawal = Transaction.withdrawal(initialBalance, moreThanOneThousand);
        // then
        assertThrows(AccountLimitsExceededException.class, () -> limits.validate(deposit));
        assertThrows(AccountLimitsExceededException.class, () -> limits.validate(withdrawal));
    }

    @Test
    void validatingTransactionWithAmountWithinLimits_ShouldNotThrowAccountLimitsExceededException() {
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