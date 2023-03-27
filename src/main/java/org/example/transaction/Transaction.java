package org.example.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public abstract class Transaction {

    private final String type;

    private final Money initialBalance;

    private final Money amount;

    private LocalDateTime date;

    private Money result;


    Transaction(Money initialBalance, Money amount, String type) {
        this.type = type;
        this.initialBalance = initialBalance;
        this.amount = amount;
    }

    public abstract void run();

    public static Transaction deposit(Money balance, Money amount) throws InvalidTransactionRequestException {
        if (isNegativeValue(amount)) throw new InvalidTransactionRequestException("Negative Value");
        return new Deposit(balance, amount);
    }

    public static Transaction withdrawal(Money balance, Money amount) {
        if (isNegativeValue(amount)) throw new InvalidTransactionRequestException("Negative Value");
        if (amount.greaterThan(balance)) throw new InsufficientFundsException("Insufficient Funds");
        return new Withdrawal(balance, amount);
    }

    public static Transaction nullTransaction() {
        return NullTransaction.getInstance();
    }

    private static boolean isNegativeValue(Money amount) {
        Money zero = new Money(new BigDecimal("0.00"));
        return amount.lessThan(zero);
    }

    public Money getInitialBalance() {
        return this.initialBalance;
    }

    public Money getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    public Money getResult() {
        return this.result;
    }

    public boolean isNull() {
        return false;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    void setResult(Money result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!Objects.equals(type, that.type)) return false;
        if (!Objects.equals(initialBalance, that.initialBalance))
            return false;
        if (!Objects.equals(amount, that.amount)) return false;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (initialBalance != null ? initialBalance.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
