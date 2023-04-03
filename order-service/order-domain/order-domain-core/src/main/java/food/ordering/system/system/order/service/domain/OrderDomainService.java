package food.ordering.system.system.order.service.domain;

import food.ordering.system.system.order.service.domain.entity.Order;
import food.ordering.system.system.order.service.domain.entity.Restaurant;
import food.ordering.system.system.order.service.domain.event.OrderCancelledEvent;
import food.ordering.system.system.order.service.domain.event.OrderCreatedEvent;
import food.ordering.system.system.order.service.domain.event.OrderPaidEvent;

import java.util.List;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

/**
 * <p>OrderDomainService Class is an interface for order domain entity, the implementation won't be defined here.</p>
 * <p>In application service. Domain layer should not know about how to fire the event.</p>
 * <p>In Domain Service or Entity, we create events.</p>
 */
public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
