package com.food.ordering.system.order.service.domain.ports.output.repository;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */


public interface OrderRepository {
    /**
     * Pass domain Entity here and repository impl will convert domain entity class into JPA entity objects
     * @param order
     * @return Order
     */
    Order save(Order order);

    /**
     * Uses Optional of Order as return type. May or may not find Order by Tracking Id
     * @param trackingId
     * @return Optional Order
     */
    Optional<Order> findByTrackingId(TrackingId trackingId);

}
