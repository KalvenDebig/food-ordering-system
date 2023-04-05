package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public class OrderCreatedEvent extends OrderEvent {
    public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
