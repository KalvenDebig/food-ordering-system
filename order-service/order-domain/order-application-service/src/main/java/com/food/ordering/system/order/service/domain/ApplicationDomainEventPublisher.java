package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author kalvens on 4/4/23
 * @project food-ordering-system
 */

/**
 * <p>Implemented ApplicationEventPublisherAware interface and DomainEventPublisher interface, use the set method in
 * ApplicationEventPublisherAware to set the spring bean in this class field.
 * </p>
 * <p>Use publishEvent method inside ApplicationEventPublisher to publish orderCreatedEvent</p>
 */
@Component
@Slf4j
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware,
        DomainEventPublisher<OrderCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("OrderCreatedEvent is published for order id: {}", domainEvent.getOrder().getId().getValue());
    }
}
