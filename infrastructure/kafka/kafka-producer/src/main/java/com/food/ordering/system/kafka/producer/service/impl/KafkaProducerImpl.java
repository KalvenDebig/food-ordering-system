package com.food.ordering.system.kafka.producer.service.impl;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;

/**
 * @author kalvens on 4/5/23
 * @project food-ordering-system
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {
    private final KafkaTemplate<K, V> kafkaTemplate;

    /**
     * <p>In summary, when you call KafkaTemplate.send() in Spring Kafka, it creates a ProducerRecord object and uses a
     * KafkaProducer instance from the ProducerFactory to send the message to the Kafka broker. The send() method
     * returns a ListenableFuture that can be used to receive a confirmation of the send operation, and if
     * configured to use transactions, the method will participate in the current transaction.</p>
     * @param topicName
     * @param key
     * @param message
     * @param callback
     */
    @Override
    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            ListenableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.addCallback(callback);
        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, topic: {}, and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message " + message);
        }
    }

    /**
     * When application is shutting down, @PreDestroy marked methods will be used
     */
    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing Kafka Producer");
            kafkaTemplate.destroy();
        }
    }
}
