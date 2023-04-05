package com.food.ordering.system.domain.valueobject;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

import java.util.Objects;

/**
 * <p>Constructor marked as protected, only available for subclasses, and that's enough</p>
 * @param <T>
 */
public abstract class BaseId<T> {
    private final T value;
    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
