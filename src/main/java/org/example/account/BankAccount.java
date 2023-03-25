package org.example.account;

import org.example.transaction.*;

import java.util.List;

public class BankAccount {

    private final String customerName;

    private final String customerId;

    private Money balance;

    private final TransactionHistory transactionHistory;

    public BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.transactionHistory = new InMemoryTransactionHistory();
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

    public Transaction getLastTransaction() {
        return transactionHistory.getLast();
    }

    public void deposit(Money amount) {
        try {
            Transaction deposit = Transaction.deposit(balance, amount);
            run(deposit);
        } catch (InvalidTransactionRequestException ex) {
            throw new UnauthorizedBankOperationException(ex.getMessage());
        }
    }

    public void withdraw(Money amount) {
        try {
            Transaction withdrawal = Transaction.withdrawal(balance, amount);
            run(withdrawal);
        } catch (InvalidTransactionRequestException | InsufficientFundsException ex) {
            throw new UnauthorizedBankOperationException(ex.getMessage());
        }
    }

    private void run(Transaction transaction) {
        transaction.run();
        balance = transaction.getResult();
        transactionHistory.save(transaction);
    }
}
