package com.food.ordering.system.kafka.producer.exception;

/**
 * @author kalvens on 4/5/23
 * @project food-ordering-system
 */
public class KafkaProducerException extends RuntimeException {
    public KafkaProducerException(String message) {
        super(message);
    }
}
