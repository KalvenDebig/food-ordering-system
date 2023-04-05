package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.valueobject.*;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.create.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Customer;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import com.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author kalvens on 4/4/23
 * @project food-ordering-system
 */

/**
 * <p>@SpringBootTest annotation here will create all the beans we need, defined in OrderTestConfiguration class</p>
 * <p>@TestInstance will create single instance for this class before BeforeAll and those instances can be
 * non-static instances</p>
 * <p>Repositories are marked as Mocked Beans in Configuration class</p>
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {
    @Autowired
    private OrderApplicationService orderApplicationService;
    @Autowired
    private OrderDataMapper orderDataMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private CreateOrderCommand createOrderCommand;
    private CreateOrderCommand createOrderCommandWrongPrice;
    private CreateOrderCommand createOrderCommandWrongProductPrice;

    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID RESTAURANT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID ORDER_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");
    private final UUID SAGA_ID = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afa");
    private final BigDecimal PRICE = new BigDecimal("200.00");


    @BeforeAll
    public void init() {
        createOrderCommand = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ID)
                .address(OrderAddress.builder()
                        .city("City")
                        .street("Street")
                        .postalCode("Code")
                        .build())
                .price(PRICE)
                .items(List.of(OrderItem.builder()
                                        .productId(PRODUCT_ID)
                                        .price(new BigDecimal("50.00"))
                                        .quantity(1)
                                        .subtotal(new BigDecimal("50.00"))
                                .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .price(new BigDecimal("50.00"))
                                .quantity(3)
                                .subtotal(new BigDecimal("150.00"))
                                .build()))
                .build();

        createOrderCommandWrongPrice = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ID)
                .address(OrderAddress.builder()
                        .city("City")
                        .street("Street")
                        .postalCode("Code")
                        .build())
                .price(new BigDecimal("1000.00"))
                .items(List.of(OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .price(new BigDecimal("50.00"))
                                .quantity(1)
                                .subtotal(new BigDecimal("50.00"))
                        .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .price(new BigDecimal("50.00"))
                                .quantity(3)
                                .subtotal(new BigDecimal("150.00"))
                                .build()))
                .build();

        createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ID)
                .address(OrderAddress.builder()
                        .city("City")
                        .street("Street")
                        .postalCode("Code")
                        .build())
                .price(new BigDecimal("210.00"))
                .items(List.of(OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .price(new BigDecimal("60.00"))
                                .quantity(1)
                                .subtotal(new BigDecimal("60.00"))
                        .build(),
                        OrderItem.builder()
                                .productId(PRODUCT_ID)
                                .price(new BigDecimal("50.00"))
                                .quantity(3)
                                .subtotal(new BigDecimal("150.00"))
                                .build()))
                .build();

        Customer customer = new Customer(new CustomerId(CUSTOMER_ID));

        Restaurant restaurantResponse = Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(List.of(
                        new Product(new ProductId(PRODUCT_ID), "Product", new Money(new BigDecimal("50.00"))),
                        new Product(new ProductId(PRODUCT_ID), "Product2",
                                new Money(new BigDecimal("50.00"))))
                )
                .active(true)
                .build();

        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        order.setId(new OrderId(ORDER_ID));

        when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(restaurantRepository.findRestaurantInformation(orderDataMapper.
                createOrderCommandToRestaurant(createOrderCommand))).thenReturn(Optional.of(restaurantResponse));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

    }

    /**
     * <p>Test order status should be pending, correct message after going through createOrder method, and has a
     * tracking id</p>
     */
    @Test
    public void testCreateOrder() {
        CreateOrderResponse response = orderApplicationService.createOrder(createOrderCommand);

        assertEquals(OrderStatus.PENDING, response.getOrderStatus());
        assertEquals("Order created successfully.", response.getMessage());
        assertNotNull(response.getOrderTrackingId());
    }

    @Test
    public void testCreateOrderWithWrongSubtotal() {
        assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandWrongPrice));
    }

    @Test
    public void testCreateOrderWithWrongProductPrice() {
        assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice));
    }

    @Test
    public void testCreateOrderWithPassiveRestaurant() {
        Restaurant restaurantResponse = Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(List.of(new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
                        new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))))
                .active(false)
                .build();
        when(restaurantRepository.findRestaurantInformation(orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)))
                .thenReturn(Optional.of(restaurantResponse));
        assertThrows(OrderDomainException.class,
                () -> orderApplicationService.createOrder(createOrderCommand));
    }
}
