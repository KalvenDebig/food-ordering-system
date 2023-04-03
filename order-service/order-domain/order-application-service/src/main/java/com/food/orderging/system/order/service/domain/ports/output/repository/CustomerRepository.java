package com.food.orderging.system.order.service.domain.ports.output.repository;

import food.ordering.system.system.order.service.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public interface CustomerRepository {
    /**
     * May or may not find customer by customerId. Impl will be provided in data access layer
     * @param customerId
     * @return Optional of Customers
     */
    Optional<Customer> findCustomer(UUID customerId);
}
