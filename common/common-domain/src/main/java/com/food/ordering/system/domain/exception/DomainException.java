package com.food.ordering.system.domain.exception;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>Common DomainException class, each Domain related Exception will extend this class</p>
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
