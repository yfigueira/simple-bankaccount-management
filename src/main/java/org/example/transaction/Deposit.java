package org.example.transaction;

import java.util.Date;

public class Deposit extends Transaction {

    public Deposit(Money initialBalance, Money amount) {
        super(initialBalance, amount, "deposit");
    }

    @Override
    public void run() {
        setDate(new Date());
        setResult(getInitialBalance().plus(getAmount()));
    }
}
