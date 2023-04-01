package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>All value objects should be immutable</p>
 * <p>Value in the fields will not be changed since they are marked as final</p>
 * <p>Use aggregate root, reduces errors</p>
 * <p>Because of BigDecimal(double val) constructor, result is somehow unpredictable</p>
 */
public class Money {
    public final static Money ZERO = new Money(BigDecimal.ZERO);
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    /**
     * <p>If the amount is not null and amount is larger than 0, then return true</p>
     * <p>Reason for not using equals()</P>
     * <p>amount = 0.00 -> amount.compareTo(BigDecimal.ZERO) > 0 -> return false</p>
     * <p>Otherwise true</p>
     *  @return boolean value
     */
    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Check if this money objects has amount larger than the parameter money
     * @param money
     * @return boolean value
     */
    public boolean isGreaterThan(Money money) {
        return this.amount != null && this.amount.compareTo(money.getAmount()) > 0;
    }

    /**
     * <p>Use BigDecimal add() method, add parameter value to current object field</p>
     * <p>Use setScale to truncate a same value every time</p>
     * @param money
     * @return Money Object
     */
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    /**
     * <p>Use BigDecimal subtract() method, subtract parameter value from current object field</p>
     * @param money
     * @return Money Object
     */
    public Money subtract(Money money) {
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }

    /**
     * <p>Use BigDecimal multiply() method, multiply parameter value with current object field</p>
     * @param money
     * @return Money Object
     */
    public Money multiply(int money) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(money))));
    }

    /**
     * <p>setScale() example</p>
     * <p>With scale 2, the number of digits after decimal point is 2, e.g. 10.75, 500.80</p>
     * <p>Also because Java uses available bits to represent repeating fraction numbers</p>
     * <p>Double 0.7 = 111/1010 = 0.10110011001100...11 (52 bits) -> Bits after 52 are truncated</p>
     * @param input
     * @return BigDecimal Object
     */
    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}
