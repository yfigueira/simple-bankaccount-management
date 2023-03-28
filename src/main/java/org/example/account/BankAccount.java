package org.example.account;

import org.example.transaction.*;

import java.math.BigDecimal;
import java.util.List;

public class BankAccount {

    private final String customerName;

    private final String customerId;

    private Money balance;

    private final TransactionHistory transactionHistory;

    private final AccountLimit depositLimit;

    private final AccountLimit withdrawalLimit;

    public BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.transactionHistory = new InMemoryTransactionHistory();
        this.depositLimit = new AccountLimit();
        this.withdrawalLimit = new AccountLimit();
        this.balance = new Money(new BigDecimal("3000.00"));
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
            depositLimit.validate(deposit);
            run(deposit);
        } catch (AccountLimitExceededException ex) {
            throw new UnauthorizedBankOperationException(ex.getMessage());
        }
    }

    public void withdraw(Money amount) {
        try {
            Transaction withdrawal = Transaction.withdrawal(balance, amount);
            withdrawalLimit.validate(withdrawal);
            run(withdrawal);
        } catch (AccountLimitExceededException ex) {
            throw new UnauthorizedBankOperationException(ex.getMessage());
        }
    }

    private void run(Transaction transaction) {
        try {
            transaction.run();
            balance = transaction.getResult();
            transactionHistory.save(transaction);
        } catch (InvalidTransactionRequestException ex) {
            throw new UnauthorizedBankOperationException(ex.getMessage());
        }
    }

    public void setDepositLimit(Money amount) {
        depositLimit.setTransactionLimit(amount);
    }

    public void setWithdrawalLimit(Money amount) {
        withdrawalLimit.setTransactionLimit(amount);
    }
}
