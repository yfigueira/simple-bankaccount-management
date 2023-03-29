package org.example.transaction;

import org.example.money.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class InMemoryTransactionHistoryTest {

    private InMemoryTransactionHistory history;

    @BeforeEach
    void setUp() {
        this.history = new InMemoryTransactionHistory();
    }

    @AfterEach
    void tearDown() {
        this.history = null;
    }

    @Test
    void whenInitialized_ShouldBeEmpty() {
        // given field history
        // then
        assertThat(history.getAll(), is(emptyCollectionOf(Transaction.class)));
    }

    @Test
    void afterSave_ShouldNotBeEmpty() {
        // given field history and:
        Money balance = new Money(new BigDecimal("1000.00"));
        Money amount = new Money(new BigDecimal("100.00"));
        Transaction deposit = Transaction.deposit(balance, amount);
        // when
        history.save(deposit);
        // then
        assertThat(history.getAll(), is(not(emptyCollectionOf(Transaction.class))));
    }

    @Test
    void afterSavingTwoTransactions_ShouldHaveTwoTransactionsSaved() {
        // given field history and:
        Money balance = new Money(new BigDecimal("1000.00"));
        Money amount = new Money(new BigDecimal("100.00"));
        // when
        Transaction deposit = Transaction.deposit(balance, amount);
        history.save(deposit);

        Transaction withdrawal = Transaction.withdrawal(balance, amount);
        history.save(withdrawal);
        // then
        assertThat(history.getAll().size(), is(equalTo(2)));
    }

    @Test
    void requestingLastTransaction_ShouldReturnLastTransactionSaved() {
        // given field history and:
        Money balance = new Money(new BigDecimal("1000.00"));
        Money amount = new Money(new BigDecimal("200.00"));
        // when
        Transaction withdrawal = Transaction.withdrawal(balance, amount);
        history.save(withdrawal);

        Transaction deposit = Transaction.deposit(balance, amount);
        history.save(deposit);
        // then
        assertThat(history.getLast(), is(equalTo(deposit)));
    }

    @Test
    void requestingLastTransactionFromEmptyHistory_ShouldReturnNull() {
        // given field history
        // then
        assertThat(history.getLast(), is(equalTo(null)));
    }

    @Test
    void requestingAllTransactionsFromEmptyTransactionHistory_ShouldReturnEmptyListOfTransactions() {
        // given field history
        // then
        assertThat(history.getAll(), is(emptyCollectionOf(Transaction.class)));
    }
}
