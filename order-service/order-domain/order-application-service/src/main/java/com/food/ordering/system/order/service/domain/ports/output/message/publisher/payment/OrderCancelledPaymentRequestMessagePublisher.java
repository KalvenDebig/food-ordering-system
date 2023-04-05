package com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
