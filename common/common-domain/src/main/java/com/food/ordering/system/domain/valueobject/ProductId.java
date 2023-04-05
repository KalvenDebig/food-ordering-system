package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>Use UUID as ID type</p>
 * <p>ProductId class, use get methods to get the product id value</p>
 */
public class ProductId extends BaseId<UUID> {
    public ProductId(UUID value) {
        super(value);
    }
}
