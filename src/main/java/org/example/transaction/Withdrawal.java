package org.example.transaction;

import java.time.LocalDateTime;
import java.util.Date;

public class Withdrawal extends Transaction{

    public Withdrawal(Money initialBalance, Money amount) {
        super(initialBalance, amount, "withdrawal");
    }

    @Override
    public void run() {
        setDate(LocalDateTime.now());
        setResult(getInitialBalance().minus(getAmount()));
    }
}
