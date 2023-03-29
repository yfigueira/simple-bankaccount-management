package org.example.transaction;

import java.time.LocalDateTime;

public class Withdrawal extends Transaction{

    public Withdrawal(Money initialBalance, Money amount) {
        super(initialBalance, amount, TransactionType.WITHDRAWAL);
    }

    @Override
    public void run() {
        if (isNegativeValue(getAmount())) throw new InvalidTransactionRequestException("Negative Value");
        if (getInitialBalance().lessThan(getAmount())) throw new InvalidTransactionRequestException("Insufficient Funds");
        setDate(LocalDateTime.now());
        setResult(getInitialBalance().minus(getAmount()));
    }
}
