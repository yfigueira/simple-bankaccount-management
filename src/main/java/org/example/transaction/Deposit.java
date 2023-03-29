package org.example.transaction;

import java.time.LocalDateTime;

public class Deposit extends Transaction {

    public Deposit(Money initialBalance, Money amount) {
        super(initialBalance, amount, TransactionType.DEPOSIT);
    }

    @Override
    public void run() {
        if (isNegativeValue(getAmount())) throw new InvalidTransactionRequestException("Negative Value");
        setDate(LocalDateTime.now());
        setResult(getInitialBalance().plus(getAmount()));
    }
}
