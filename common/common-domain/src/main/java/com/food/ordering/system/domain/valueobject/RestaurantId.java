package com.food.ordering.system.domain.valueobject;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

import java.util.UUID;

/**
 * <p>Use UUID for RestaurantId class</P>
 * <p>Use getValue() method provided by BaseId to get the id value</P>
 */
public class RestaurantId extends BaseId<UUID> {
    public RestaurantId(UUID value) {
        super(value);
    }
}
