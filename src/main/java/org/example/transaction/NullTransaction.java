package org.example.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NullTransaction extends Transaction {

    private static NullTransaction instance;

    private NullTransaction(Money initialBalance, Money amount) {
        super(initialBalance, amount, "undefined");
    }

    public static Transaction getInstance() {
        if (instance == null) {
            Money defaultAmount = new Money(new BigDecimal("0.00"));
            return new NullTransaction(defaultAmount, defaultAmount);
        }
        return instance;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public void run() {
        setDate(LocalDateTime.MIN);
        setResult(new Money(new BigDecimal("0.00")));
    }
}
