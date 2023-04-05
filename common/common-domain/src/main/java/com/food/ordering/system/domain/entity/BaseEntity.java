package com.food.ordering.system.domain.entity;

/**
 * @Project food-ordering-system
 * @Author kalvens on 3/31/23
 */

import java.util.Objects;

/**
 * <p>BaseEntity Class, will be used in order-domain-core</p>
 * <p>Any class extends BaseEntity will pass their own identifier</p>
 * <p>Provided getter and setter methods here</p>
 * <p>equals() and hashCode() are based on the id field </p>
 * @param <ID>
 * @since 1.0-SNAPSHOT
 */
public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
