package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * @author kalvens on 4/4/23
 * @project food-ordering-system
 */
public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
