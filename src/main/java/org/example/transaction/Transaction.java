package org.example.transaction;

import org.example.money.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Transaction {

    private final TransactionType type;

    private final Money initialBalance;

    private final Money amount;

    private LocalDateTime date;

    private Money result;


    Transaction(Money initialBalance, Money amount, TransactionType type) {
        this.type = type;
        this.initialBalance = initialBalance;
        this.amount = amount;
    }

    public abstract void run() throws InvalidTransactionRequestException;

    public static Transaction deposit(Money balance, Money amount) throws InvalidTransactionRequestException {
        return new Deposit(balance, amount);
    }

    public static Transaction withdrawal(Money balance, Money amount) {
        return new Withdrawal(balance, amount);
    }

    static boolean isNegativeValue(Money amount) {
        Money zero = new Money(new BigDecimal("0.00"));
        return amount.lessThan(zero);
    }

    public Money getInitialBalance() {
        return this.initialBalance;
    }

    public Money getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public TransactionType getType() {
        return this.type;
    }

    public Money getResult() {
        return this.result;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    void setResult(Money result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(initialBalance, that.initialBalance))
            return false;
        if (!Objects.equals(amount, that.amount)) return false;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (initialBalance != null ? initialBalance.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
