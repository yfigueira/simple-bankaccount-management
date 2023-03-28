package org.example.account;

public class AccountLimitExceededException extends IllegalArgumentException {

    public AccountLimitExceededException(String message) {
        super(message);
    }
}
