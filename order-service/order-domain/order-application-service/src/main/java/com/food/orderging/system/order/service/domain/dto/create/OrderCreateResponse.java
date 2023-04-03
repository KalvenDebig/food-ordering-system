package com.food.orderging.system.order.service.domain.dto.create;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

@Getter
@Builder
@AllArgsConstructor
public class OrderCreateResponse {
    @NotNull
    private final UUID orderTrackingId;
    @NotNull
    private final OrderStatus orderStatus;
    @NotNull
    private final String message;
}
