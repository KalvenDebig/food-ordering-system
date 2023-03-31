package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>Will be used in Order, OrderItem, PaymentService, RestaurantService</p>
 * <p>Value objects should be immutable, in terms of fields, in BaseId, id field is final, so this object is
 * immutable</p>
 */
public class OrderId extends BaseId<UUID> {
    public OrderId(UUID value) {
        super(value);
    }
}
