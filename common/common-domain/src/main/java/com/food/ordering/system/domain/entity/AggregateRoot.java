package com.food.ordering.system.domain.entity;

/**
 * @project food-ordering-system
 * @author kalvens on 3/31/23
 */

/**
 * <p>Simply a marker class, tell which class is a aggregate</p>
 * <p>Distinguish the root class and other entities</p>
 * @param <ID>
 */
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {
}
