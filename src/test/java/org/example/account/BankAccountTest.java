package org.example.account;

import org.example.transaction.Money;
import org.example.transaction.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    private String customerName;

    private String customerId;

    private BankAccount account;

    @BeforeEach
    void setUp() {
        this.customerName = "John";
        this.customerId = "id-123";
        this.account = new BankAccount(customerName, customerId);
    }

    @AfterEach
    void tearDown() {
        this.customerName = null;
        this.customerId = null;
    }

    @Test
    void whenInitialized_ShouldHaveCustomerCredentials() {
        // given fields customerName, customerId and account
        // then
        assertThat(account.getCustomerName(), is(not(nullValue())));
        assertThat(account.getCustomerId(), is(not(nullValue())));
    }

    @Test
    void whenInitialized_ShouldHaveEmptyTransactionsHistory() {
        // given fields customerName, customerId and account
        // then
        assertThat(account.getTransactionHistory(), is(emptyCollectionOf(Transaction.class)));
    }

    @Test
    void whenInitialized_ShouldHaveBalance() {
        // given fields customerName, customerId and account
        // then
        assertThat(account.getBalance(), is(not(nullValue())));
    }

    @Test
    void depositOneHundred_ShouldRaiseTheBalanceByOneHundred() {
        // given field account and:
        Money initialBalance = account.getBalance();
        Money oneHundred = new Money(new BigDecimal("100"));
        // when
        account.deposit(oneHundred);
        // then
        assertThat(account.getBalance(), is(equalTo(initialBalance.plus(oneHundred))));
    }

    @Test
    void depositTwoHundredAndFiftyEightPointSixtyThree_ShouldRaiseBalanceByTwoHundredAndFiftyEightPointSixtyThree() {
        // given field account and:
        Money initialBalance = account.getBalance();
        Money twoHundredAndFiftyEightPointSixtyThree = new Money(new BigDecimal("258.63"));
        // when
        account.deposit(twoHundredAndFiftyEightPointSixtyThree);
        // then
        assertThat(account.getBalance(), is(equalTo(initialBalance.plus(twoHundredAndFiftyEightPointSixtyThree))));
    }

    @Test
    void transaction_ShouldBeSavedInTransactionHistory() {
        // given field account and:
        Money amount = new Money(new BigDecimal("100.00"));
        // when
        account.deposit(amount);
        // then
        assertThat(account.getTransactionHistory(), is(not(emptyCollectionOf(Transaction.class))));
    }

    @Test
    void getLastTransaction_ShouldReturnLastTransactionSavedInHistory() {
        // given field account
        // when
        account.deposit(new Money(new BigDecimal("100.00")));
        int oneHundredDepositIndex = account.getTransactionHistory().size() - 1;
        // then
        assertThat(account.getLastTransaction(), is(equalTo(account.getTransactionHistory().get(oneHundredDepositIndex))));

        account.deposit(new Money(new BigDecimal("200.00")));

        assertThat(account.getLastTransaction(), is(not(equalTo(account.getTransactionHistory().get(oneHundredDepositIndex)))));
    }

    @Test
    void withdrawOneHundred_ShouldLowerTheBalanceByOneHundred() {
        // given field account and:
        Money initialBalance = account.getBalance();
        Money oneHundred = new Money(new BigDecimal("100.00"));
        // when
        account.withdraw(oneHundred);
        // then
        assertThat(account.getBalance(), is(equalTo(initialBalance.minus(oneHundred))));
    }

    @Test
    void depositOneHundredAndWithdrawOneHundred_ShouldGetBackToInitialBalance() {
        // given field account and:
        Money initialBalance = account.getBalance();
        Money oneHundred = new Money(new BigDecimal("100.00"));
        // when
        account.deposit(oneHundred);
        account.withdraw(oneHundred);
        // then
        assertThat(account.getBalance(), is(equalTo(initialBalance)));
    }

    @Test
    void failedTransaction_ShouldThrowUnauthorizedBankOperationException() {
        // given field account and:
        Money negativeAmount = new Money(new BigDecimal("-100.00"));

        Money one = new Money(new BigDecimal("1.00"));
        Money tooBigAmount = account.getBalance().plus(one);
        // then
        assertThrows(UnauthorizedBankOperationException.class, () -> account.deposit(negativeAmount));
        assertThrows(UnauthorizedBankOperationException.class, () -> account.withdraw(negativeAmount));
        assertThrows(UnauthorizedBankOperationException.class, () -> account.withdraw(tooBigAmount));
    }
}
