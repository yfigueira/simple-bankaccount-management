package org.example.account;

import org.example.transaction.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BankAccountTest {

    private String customerName;
    private String customerId;

    @BeforeEach
    void setUp() {
        this.customerName = "John";
        this.customerId = "id-123";
    }

    @AfterEach
    void tearDown() {
        this.customerName = null;
        this.customerId = null;
    }

    @Test
    void whenInitialized_ShouldHaveCustomerCredentials() {
        // given fields customerName and customerId
        // when
        BankAccount account = new BankAccount(customerName, customerId);
        // then
        assertThat(account.getCustomerName(), is(not(nullValue())));
        assertThat(account.getCustomerId(), is(not(nullValue())));
    }

    @Test
    void whenInitialized_ShouldHaveEmptyTransactionsHistory() {
        // given fields customerName and customerId
        BankAccount account = new BankAccount(customerName, customerId);
        // then
        assertThat(account.getTransactions(), is(emptyCollectionOf(Transaction.class)));
    }

    @Test
    void whenInitialized_ShouldHaveBalance() {
        // given fields customerName and customerId
        BankAccount account = new BankAccount(customerName, customerId);
        // then
        assertThat(account.getBalance(), is(not(nullValue())));
    }
}
