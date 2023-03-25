package org.example.transaction;

public class InvalidTransactionRequestException extends IllegalArgumentException {

    public InvalidTransactionRequestException(String message) {
        super(message);
    }
}
