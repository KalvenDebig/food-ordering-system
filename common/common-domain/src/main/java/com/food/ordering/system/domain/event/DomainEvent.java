package com.food.ordering.system.domain.event;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>Functionally, this generic type T won't be used, it's used to help understand which entity fires this event</p>
 * <p>e.g. OrderCreatedEvent -> T: Order</p>
 * @param <T>
 */
public interface DomainEvent<T> {
}
