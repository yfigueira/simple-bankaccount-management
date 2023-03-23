package org.example.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;
    private final String currency;

    Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.currency = "USD";
    }

    BigDecimal getAmount() {
        return this.amount;
    }

    Money plus(Money augend) {
        return new Money(this.amount.add(augend.amount));
    }

    Money minus(Money subtrahend) {
        return new Money(this.amount.subtract(subtrahend.amount));
    }

    boolean greaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!Objects.equals(amount, money.amount)) return false;
        return Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }
}
