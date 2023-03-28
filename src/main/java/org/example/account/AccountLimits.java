package org.example.account;

import org.example.transaction.Money;
import org.example.transaction.Transaction;

import java.math.BigDecimal;

public class AccountLimits {

    private Money depositLimit;
    private Money withdrawalLimit;

    AccountLimits() {
        this.depositLimit = new Money(new BigDecimal("1000.00"));
        this.withdrawalLimit = new Money(new BigDecimal("1000.00"));
    }

    public Money getDepositLimit() {
        return depositLimit;
    }

    public Money getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setDepositLimit(Money depositLimit) {
        if (isLessThanZero(depositLimit)) throw new UnauthorizedBankOperationException("Deposit Limit Must Be Greater Than Zero");
        this.depositLimit = depositLimit;
    }

    public void setWithdrawalLimit(Money withdrawalLimit) {
        if (isLessThanZero(withdrawalLimit)) throw new UnauthorizedBankOperationException("Withdrawal Limit Must Be Greater Than Zero");
        this.withdrawalLimit = withdrawalLimit;
    }

    private boolean isLessThanZero(Money amount) {
        Money zero = new Money(new BigDecimal("0.00"));
        return amount.lessThan(zero);
    }

    public void validate(Transaction transaction) throws AccountLimitsExceededException {
        if (transaction.getType().equals("deposit")) validateDeposit(transaction);
        validateWithdrawal(transaction);
    }

    private void validateWithdrawal(Transaction transaction) {
        if (transaction.getAmount().greaterThan(withdrawalLimit)) throw new AccountLimitsExceededException("Exceeded Withdrawal Limit");
    }

    private void validateDeposit(Transaction transaction) {
        if (transaction.getAmount().greaterThan(depositLimit)) throw new AccountLimitsExceededException("Exceeded Deposit Limit");
    }
}
