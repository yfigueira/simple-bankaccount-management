package org.example.transaction;

public class Deposit extends Transaction {

    public Deposit(Money initialBalance, Money amount) {
        super(initialBalance, amount, "deposit");
    }

    @Override
    public Money getResult() {
        return getInitialBalance().plus(getAmount());
    }
}
