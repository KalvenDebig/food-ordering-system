package food.ordering.system.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.*;
import food.ordering.system.system.order.service.domain.exception.OrderDomainException;
import food.ordering.system.system.order.service.domain.valueobject.OrderItemId;
import food.ordering.system.system.order.service.domain.valueobject.StreetAddress;
import food.ordering.system.system.order.service.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>Order class is a AggregateRoot, and OrderId class is passed in, which means the Id type for AggregateRoot and
 * BaseEntity is OrderId and we use UUID for OrderId</p>
 */
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;

    /**
     * <h3>These three fields are not final, their value might be changed.</h3>
     */
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    /** Business Logic **/
    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1L;
        for (OrderItem orderItem : items) {
            orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId ++));
        }
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    /**
     * <p>Change order status from PENDING to PAID</p>
     * @exception OrderDomainException
     */
    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        orderStatus = OrderStatus.PAID;
    }

    /**
     * Change order status from PAID to APPROVE
     * @exception OrderDomainException
     */
    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for approve operation");
        }

        orderStatus = OrderStatus.APPROVED;
    }

    /**
     * Change order status from PAID to CANCELLING
     * failureMessages will be attached after current failure message
     * @param failureMessages
     * @exception OrderDomainException
     */
    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct status for init cancel operation");
        }

        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessage(failureMessages);
    }

    /**
     * Change order to CANCELLED, can only happen when previously was PAID or CANCELLING.
     * failureMessage will be attached after current failure message
     * @param failureMessages
     * @exception OrderDomainException
     */
    public void cancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID && orderStatus != OrderStatus.CANCELLING) {
            throw new OrderDomainException("Order is not in correct status for cancel operation");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessage(failureMessages);
    }

    private void updateFailureMessage(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages);
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    /**
     * <p>Validate if the order has been created</p>
     * @exception OrderDomainException
     */
    private void validateInitialOrder() {
        if (orderStatus != null || getId() != null) {
            throw new OrderDomainException("Order is not in the correct state for initialization.");
        }

    }

    /**
     * <p>Validate items total price is not null and greater than zero</p>
     * @exception OrderDomainException
     */
    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero.");
        }
    }

    /**
     * <p>Validate each item in item list, if they are having the correct price</p>
     * <p>Validate sum of all item price and compare with subtotal in this order</p>
     * @exception OrderDomainException
     */
    private void validateItemsPrice() {
        Money orderItemsTotal = items.stream().map(orderItem -> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotal)) {
            throw new OrderDomainException("Total Price: " + price.getAmount() + " is not equal to Order items total:" +
                    orderItemsTotal.getAmount());
        }
    }

    /**
     * <p>Will be called when looping each item</p>
     * @param orderItem
     * @exception OrderDomainException
     */
    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price " + orderItem.getPrice().getAmount() + " is not valid " +
                    "for product " + orderItem.getProduct().getId().getValue());
        }
    }

    /** Getter and Builder **/

    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }


    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
