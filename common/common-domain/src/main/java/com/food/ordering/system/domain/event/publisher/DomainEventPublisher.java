package com.food.ordering.system.domain.event.publisher;

import com.food.ordering.system.domain.event.DomainEvent;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
