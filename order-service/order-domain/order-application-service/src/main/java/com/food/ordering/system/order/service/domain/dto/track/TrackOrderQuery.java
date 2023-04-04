package com.food.ordering.system.order.service.domain.dto.track;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * Service will use this class to the latest status of an order
 * Response type will be TrackOrderResponse
 */

@Builder
@Getter
@AllArgsConstructor
public class TrackOrderQuery {
    private final UUID orderTrackingId;
}
