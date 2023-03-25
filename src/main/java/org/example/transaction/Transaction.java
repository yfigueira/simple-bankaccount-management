package org.example.transaction;

import java.util.Date;
import java.util.Objects;

public abstract class Transaction {

    private final String type;

    private final Money initialBalance;

    private final Money amount;

    private Date date;

    Transaction(Money initialBalance, Money amount, String type) {
        this.type = type;
        this.initialBalance = initialBalance;
        this.amount = amount;
    }

    public abstract Money getResult();
    public void setDate() {
        this.date = new Date();
    }

    public static Transaction deposit(Money balance, Money amount) {
        return new Deposit(balance, amount);
    }

    public Money getInitialBalance() {
        return this.initialBalance;
    }

    public Money getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
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
