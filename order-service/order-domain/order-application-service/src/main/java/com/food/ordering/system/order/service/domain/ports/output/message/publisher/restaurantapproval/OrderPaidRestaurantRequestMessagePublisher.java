package com.food.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
