package food.ordering.system.system.order.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import food.ordering.system.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public class OrderCancelledEvent extends OrderEvent {
    public OrderCancelledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
