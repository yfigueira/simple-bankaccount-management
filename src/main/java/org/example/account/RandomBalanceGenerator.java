package org.example.account;

import org.example.transaction.Money;

import java.math.BigDecimal;
import java.util.Random;

public class RandomBalanceGenerator {

    static Money generate() {
        Random random = new Random();
        double balance = (random.nextDouble() + 0.01d) * 10_000d;

        return new Money(new BigDecimal(balance));
    }
}
