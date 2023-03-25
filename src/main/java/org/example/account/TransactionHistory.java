package org.example.account;

import org.example.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {

    private List<Transaction> transactions;

    TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    void save(Transaction transaction) {
        transactions.add(transaction);
    }

    List<Transaction> getAll() {
        return this.transactions;
    }
}
