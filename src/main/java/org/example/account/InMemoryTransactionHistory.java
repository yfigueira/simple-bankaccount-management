package org.example.account;

import org.example.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionHistory implements TransactionHistory {

    private final List<Transaction> transactions;

    InMemoryTransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return this.transactions;
    }

    @Override
    public Transaction getLast() {
        return transactions.get(transactions.size() - 1);
    }
}
