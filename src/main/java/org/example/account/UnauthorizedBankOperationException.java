package org.example.account;

public class UnauthorizedBankOperationException extends RuntimeException {

    public UnauthorizedBankOperationException(String message) {
        super(message);
    }
}
