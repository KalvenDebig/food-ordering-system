package com.food.orderging.system.order.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

/**
 * Service will use OrderCreateCommand to get a OrderCreateResponse
 */
@Getter
@Builder
@AllArgsConstructor
public class OrderCreateCommand {
    @NotNull
    private final UUID customerId;
    @NotNull
    private final UUID restaurantId;
    @NotNull
    private final BigDecimal price;
    private final List<OrderItem> items;
    private final OrderAddress address;
}
