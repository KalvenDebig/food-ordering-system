package com.food.ordering.system.order.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */


public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
