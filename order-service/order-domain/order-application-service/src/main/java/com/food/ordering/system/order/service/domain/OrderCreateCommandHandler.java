package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreateCommandHandler {
    private final OrderDataMapper orderDataMapper;
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    /**
     * <p>Create Order has to be Transactional</p>
     * <p>1. Check if customer is available</p>
     * <p>2. Check if restaurant is available</p>
     * <p>3. Create Order Domain Entity</p>
     * <p>4. Create OrderCreatedEvent and this event will be fire to trigger payment service for this order</p>
     * <p>5. Save Order with Restaurant into order repository</p>
     * <p>6. Publish event by ApplicationDomainEventPublisher publish method</p>
     * <p>7. Listener will be OrderCreatedEventApplicationListener</p>
     * @see ApplicationDomainEventPublisher
     * @param createOrderCommand
     * @return CreateOrderResponse
     */
    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order savedOrder = saveOrder(order);
        log.info("Order is created with order id: {}", savedOrder.getId().getValue());
        applicationDomainEventPublisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(savedOrder);
    }


    /**
     * <p>After successfully created OrderCreatedEvent call this saveOrder method to save this order into repository</p>
     * @param order
     * @return Saved Order, Domain Entity Order type.
     */
    private Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        if (savedOrder == null) {
            throw new OrderDomainException("Couldn't save order");
        }

        log.info("Order saved with order id: {}", savedOrder.getId().getValue());
        return savedOrder;
    }

    /**
     * <p>Convert CreateOrderCommand to Restaurant by using OrderDataMapper</p>
     * <p>Search existence of Restaurant in Restaurant Repository</p>
     * <p>Throw OrderDomainException if we cannot find corresponding Restaurant</p>
     * @param createOrderCommand
     * @return Restaurant
     * @exception OrderDomainException
     */
    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantInformation(restaurant);
        if (restaurantOptional.isEmpty()) {
            log.warn("Couldn't find restaurant with restaurant id: {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Couldn't find restaurant with restaurant id: " +
                    createOrderCommand.getRestaurantId());
        }
        return restaurantOptional.get();
    }

    /**
     * <p>If more customer information needs to be checked, we will implement in DomainService</p>
     * <p>Just to check the availability of a customer we can implement here</p>
     * @param customerId
     * @exception OrderDomainException
     */
    private void checkCustomer(UUID customerId) {
        Optional<Customer> customerOptional = customerRepository.findCustomer(customerId);
        if (customerOptional.isEmpty()) {
            log.warn("Couldn't find customer with customer id: {}", customerId);
            throw new OrderDomainException("Couldn't find customer with customer id: " + customerId);
        }
    }
}
