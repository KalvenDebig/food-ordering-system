package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>OrderItem Class is not a AggregateRoot, so here only implements BaseEntity, and pass OrderItemId as
 * BaseEntity Id.</p>
 * <p>orderId field is not final because this part will be set by Order that relates to this order item</p>
 * <p>Product is the actual product item that OrderItem represents</p>
 * <h3>Note: Not using Lombok to avoid any dependency</h3>
 */
public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;
    private final int quantity;
    private final Money price;
    private final Money subTotal;

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * <p>Initialize an order item, package private method, every class outside this package can't use this method</p>
     * @param orderId
     * @param orderItemId
     */
    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero()
                && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
