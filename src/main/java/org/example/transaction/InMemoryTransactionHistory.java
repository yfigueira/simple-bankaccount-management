package org.example.transaction;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionHistory implements TransactionHistory {

    private final List<Transaction> transactions;

    public InMemoryTransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }

    @Override
    public Transaction getLast() {
        if (transactions.isEmpty()) return null;
        return transactions.get(transactions.size() - 1);
    }
}
