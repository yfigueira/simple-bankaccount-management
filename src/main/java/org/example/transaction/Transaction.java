package org.example.transaction;

import java.util.Date;

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
}
