package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

/**
 * <p>OrderApplicationServiceImpl is a package private class, only expose interface to the domain layer clients</p>
 */
@Validated
@Slf4j
@Service
@RequiredArgsConstructor
class OrderApplicationServiceImpl implements OrderApplicationService {
    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand orderCreateCommand) {
        return orderCreateCommandHandler.createOrder(orderCreateCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
