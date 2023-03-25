package org.example.transaction;

public class InsufficientFundsException extends IllegalArgumentException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}
