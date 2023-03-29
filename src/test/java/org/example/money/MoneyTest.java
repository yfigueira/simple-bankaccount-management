package org.example.money;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MoneyTest {

    private Money ten;
    private Money twenty;

    @BeforeEach
    void setUp() {
        ten = new Money(new BigDecimal("10.00"));
        twenty = new Money((new BigDecimal("20.00")));
    }

    @AfterEach
    void tearDown() {
        ten = null;
        twenty = null;
    }

    @Test
    void addition_ShouldReturnNewMoneyObject() {
        // given fields ten and twenty
        // when
        Money sum = ten.plus(twenty);
        // then
        assertThat(sum, not(sameInstance(ten)));
        assertThat(sum, not(sameInstance(twenty)));
    }

    @Test
    void subtraction_ShouldReturnNewMoneyObject() {
        // given fields ten and twenty
        // when
        Money difference = twenty.minus(ten);
        // then
        assertThat(difference, not(sameInstance(ten)));
        assertThat(difference, not(sameInstance(twenty)));
    }

    @Test
    void moneyObjects_ShouldBeEqualWhenHavingSameAmount() {
        // given field ten and:
        Money alsoTen = new Money(new BigDecimal("10.00"));
        // then
        assertThat(ten, is(equalTo(alsoTen)));
    }

    @Test
    void tenPlusTwenty_ShouldEqualThirty() {
        // given fields ten and twenty and:
        Money thirty = new Money(new BigDecimal("30.00"));
        // when
        Money sum = ten.plus(twenty);
        // then
        assertThat(sum, is(equalTo(thirty)));
    }

    @Test
    void twentyMinusTen_ShouldEqualTen() {
        // given fields ten and twenty
        // then
        assertThat(twenty.minus(ten), is(equalTo(ten)));
    }

    @Test
    void roundedAmounts_ShouldBeRoundedToNearestNeighbour() {
        // given
        Money tenPointFiveFiveOne = new Money(new BigDecimal("10.551"));
        Money tenPointFiveFive = new Money(new BigDecimal("10.55"));

        Money twoPointFiveFiveSix = new Money(new BigDecimal("2.556"));
        Money twoPointFiveSix = new Money(new BigDecimal("2.56"));
        // then
        assertThat(tenPointFiveFiveOne, is(equalTo(tenPointFiveFive)));
        assertThat(twoPointFiveFiveSix, is(equalTo(twoPointFiveSix)));
    }

    @Test
    void roundedAmounts_WhenImpossibleToDetermineNearestNeighbour_ShouldBeRoundedToEvenNeighbour() {
        // given
        Money twoPointFiveFiveFive = new Money(new BigDecimal("2.555"));
        Money twoPointFiveSix = new Money(new BigDecimal("2.56"));

        Money twoPointFourFourFive = new Money(new BigDecimal("2.445"));
        Money twoPointFourFour = new Money(new BigDecimal("2.44"));
        // then
        assertThat(twoPointFiveFiveFive, is(equalTo(twoPointFiveSix)));
        assertThat(twoPointFourFourFive, is(equalTo(twoPointFourFour)));
    }

    @Test
    void twentyGreaterThanTen_ShouldBeTrue() {
        // given fields ten and twenty
        // then
        assertThat(twenty.greaterThan(ten), is(true));
    }
}
