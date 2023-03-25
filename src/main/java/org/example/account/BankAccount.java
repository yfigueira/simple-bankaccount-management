package org.example.account;

import org.example.transaction.Money;
import org.example.transaction.Transaction;

import java.util.List;

public class BankAccount {

    private final String customerName;

    private final String customerId;

    private Money balance;

    private final TransactionHistory transactionHistory;

    public BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.transactionHistory = new TransactionHistory();
        this.balance = RandomBalanceGenerator.generate();
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public List<Transaction> getTransactionHistory() {
        return this.transactionHistory.getAll();
    }

    public Money getBalance() {
        return this.balance;
    }

    public void deposit(Money amount) {
        Transaction deposit = Transaction.deposit(balance, amount);
        balance = deposit.getResult();
        deposit.setDate();
        transactionHistory.save(deposit);
    }

    public Transaction getLastTransaction() {
        return transactionHistory.getLast();
    }
}
