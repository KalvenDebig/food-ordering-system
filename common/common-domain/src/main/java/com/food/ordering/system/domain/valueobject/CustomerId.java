package com.food.ordering.system.domain.valueobject;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

import java.util.UUID;

/**
 * <p>Use UUID as Id type for CustomerId class</p>
 * <p>Use getValue() methods provide from BaseId to get Id value</p>
 */
public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID value) {
        super(value);
    }
}
