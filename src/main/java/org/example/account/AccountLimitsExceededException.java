package org.example.account;

public class AccountLimitsExceededException extends IllegalArgumentException {

    public AccountLimitsExceededException(String message) {
        super(message);
    }
}
