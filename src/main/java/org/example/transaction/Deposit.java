package org.example.transaction;

import org.example.money.Money;

import java.time.LocalDateTime;

public class Deposit extends Transaction {

    Deposit(Money initialBalance, Money amount) {
        super(initialBalance, amount, TransactionType.DEPOSIT);
    }

    @Override
    public void run() {
        if (isNegativeValue(getAmount())) throw new InvalidTransactionRequestException("Negative Value");
        setDate(LocalDateTime.now());
        setResult(getInitialBalance().plus(getAmount()));
    }
}
