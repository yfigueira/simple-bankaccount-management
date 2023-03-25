package org.example.account;

import org.example.transaction.Transaction;

import java.util.List;

public interface TransactionHistory {

    void save(Transaction transaction);

    List<Transaction> getAll();

    Transaction getLast();
}
