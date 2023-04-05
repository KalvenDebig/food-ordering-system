package com.food.ordering.system.order.service.domain.ports.input.service;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

/**
 * <p>Client will use this class: Http Requests</p>
 * <p>Classes in input package are used to communicate with domain layer</p>
 * <p>@Valid Annotations are used in interfaces instead of implementation classes is because: </p>
 * <p>A method's preconditions (as represented by parameter constraints) must not be strengthened in sub types</p>
 */
public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
