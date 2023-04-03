package com.food.orderging.system.order.service.domain.ports.input.message.listener.restaurantapproval;

import com.food.orderging.system.order.service.domain.dto.message.RestaurantApprovalResponse;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

/**
 * Trigger by DomainEvents of RestaurantApprovalRequest
 */
public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
