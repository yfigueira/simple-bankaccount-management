package org.example.account;

import org.example.transaction.Money;
import org.example.transaction.Transaction;

import java.math.BigDecimal;

public class AccountLimit {

    private Money transactionLimit;

    AccountLimit() {
        this.transactionLimit = new Money(new BigDecimal("1000.00"));
    }

    public Money getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(Money transactionLimit) {
        if (isLessThanZero(transactionLimit)) throw new UnauthorizedBankOperationException("Transaction Limit Must Be Greater Than Zero");
        this.transactionLimit = transactionLimit;
    }

    private boolean isLessThanZero(Money amount) {
        Money zero = new Money(new BigDecimal("0.00"));
        return amount.lessThan(zero);
    }

    public void validate(Transaction transaction) throws AccountLimitExceededException {
        if (transaction.getAmount().greaterThan(transactionLimit)) throw new AccountLimitExceededException("Exceeded Transaction Limit");
    }
}
