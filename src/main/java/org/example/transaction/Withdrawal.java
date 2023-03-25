package org.example.transaction;

import java.util.Date;

public class Withdrawal extends Transaction{

    public Withdrawal(Money initialBalance, Money amount) {
        super(initialBalance, amount, "withdrawal");
    }

    @Override
    public void run() {
        setDate(new Date());
        setResult(getInitialBalance().minus(getAmount()));
    }
}
