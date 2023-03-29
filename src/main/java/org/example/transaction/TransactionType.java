package org.example.transaction;

public enum TransactionType {
    DEPOSIT("deposit"),
    WITHDRAWAL("withdrawal");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}