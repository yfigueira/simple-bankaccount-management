package org.example.account;

import org.example.transaction.Money;
import org.example.transaction.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
    void deposit_ShouldBeSavedInTransactionHistory() {
        // given field account and:
        Money amount = new Money(new BigDecimal("100.00"));
        // when
        account.deposit(amount);
        // then
        assertThat(account.getTransactionHistory(), is(not(emptyCollectionOf(Transaction.class))));
    }

    @Test
    void deposit_ShouldSetTransactionDate() {
        // given field account and:
        Money amount = new Money(new BigDecimal("100.00"));
        // when
        account.deposit(amount);
        // then
        assertThat(account.getTransactionHistory().get(0).getDate(), is(not(nullValue())));
    }

    @Test
    void getLastTransaction_ShouldReturnLastTransactionSavedInHistory() {
        // given field account and:
        // when
        account.deposit(new Money(new BigDecimal("100.00")));
        int oneHundredDepositIndex = account.getTransactionHistory().size() - 1;
        // then
        assertThat(account.getLastTransaction(), is(equalTo(account.getTransactionHistory().get(oneHundredDepositIndex))));

        account.deposit(new Money(new BigDecimal("200.00")));

        assertThat(account.getLastTransaction(), is(not(equalTo(account.getTransactionHistory().get(oneHundredDepositIndex)))));
    }
}
