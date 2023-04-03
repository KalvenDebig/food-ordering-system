package com.food.orderging.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import food.ordering.system.system.order.service.domain.event.OrderCreatedEvent;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
