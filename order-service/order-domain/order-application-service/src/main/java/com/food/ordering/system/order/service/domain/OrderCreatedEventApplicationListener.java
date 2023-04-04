package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author kalvens on 4/4/23
 * @project food-ordering-system
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedEventApplicationListener {
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;


    /**
     * <p>This method will only be called when ApplicationDomainEventPublisher commits a transaction</p>
     * @see ApplicationDomainEventPublisher
     * @param orderCreatedEvent
     */
    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent) {

    }
}
