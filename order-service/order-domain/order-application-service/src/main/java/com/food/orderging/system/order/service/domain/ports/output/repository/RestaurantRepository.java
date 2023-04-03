package com.food.orderging.system.order.service.domain.ports.output.repository;

import food.ordering.system.system.order.service.domain.entity.Restaurant;

import java.util.Optional;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */
public interface RestaurantRepository {
    /**
     * May or may not find Restaurant by similar restaurant information, will pass product ids into Product Repo in
     * Impl class
     * @param restaurant
     * @return Optional of Restaurant
     */
    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
