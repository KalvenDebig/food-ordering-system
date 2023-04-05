package com.food.ordering.system.kafka.producer;

import com.food.ordering.system.kafka.config.data.KafkaConfigData;
import com.food.ordering.system.kafka.config.data.KafkaProducerConfigData;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kalvens on 4/5/23
 * @project food-ordering-system
 */

/**
 * @param <K> For key type, it needs to be Serializable
 * @param <V> For value type, SpecificRecordBase is an abstract class for avro
 */
@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig<K extends Serializable, V extends SpecificRecordBase>{
    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducerConfigData kafkaProducerConfigData;

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigData.getBootstrapServers());
        props.put(kafkaConfigData.getSchemaRegistryUrlKey(), kafkaConfigData.getSchemaRegistryUrl());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProducerConfigData.getKeySerializerClass());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProducerConfigData.getValueSerializerClass());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProducerConfigData.getBatchSize() *
                kafkaProducerConfigData.getBatchSizeBoostFactor());
        props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProducerConfigData.getLingerMs());
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, kafkaProducerConfigData.getCompressionType());
        props.put(ProducerConfig.ACKS_CONFIG, kafkaProducerConfigData.getAcks());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaProducerConfigData.getRequestTimeoutMs());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaProducerConfigData.getRetryCount());
        return props;
    }

    /**
     * <p>In Spring Kafka, the ProducerFactory interface is used to create and configure instances of KafkaProducer,
     * which is a Kafka client that can send records to a Kafka topic.</p>
     *
     * <p>The ProducerFactory interface provides a way to encapsulate the configuration and creation of KafkaProducer
     * instances, and allows for greater flexibility and control over the creation of these instances. By using a
     * ProducerFactory, you can configure and customize the behavior of your KafkaProducer instances, such as setting
     * Kafka broker addresses, configuring serializers and deserializers for message key and value, and setting up
     * any necessary security settings.</p>
     *
     * <p>Using a ProducerFactory also allows you to pool and reuse KafkaProducer instances, which can improve
     * performance and reduce resource consumption.</p>
     *
     * <p>In summary, the ProducerFactory interface in Spring Kafka provides a way to create and configure
     * KafkaProducer instances in a flexible and customizable manner, and can improve performance and resource
     * utilization by allowing for the pooling and reuse of KafkaProducer instances.</p>
     * @return ProducerFactory
     */
    @Bean
    public ProducerFactory<K, V> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * <p>In Spring Kafka, the KafkaTemplate class is a high-level abstraction over the KafkaProducer that simplifies
     * the process of sending messages to a Kafka topic.</p>
     *
     * <p>KafkaTemplate provides a set of methods that handle the details of creating a ProducerRecord and sending it
     * to a Kafka topic. You can use the KafkaTemplate to send messages with or without a specified key, and you can
     * also specify a Partitioner to determine the partition that a message should be sent to. Additionally,
     * KafkaTemplate provides methods to send messages asynchronously and to handle message send failures.</p>
     *
     * <p>KafkaTemplate also integrates with Spring's transaction management support, allowing you to send messages
     * within a transaction and ensuring that all messages sent within the transaction are either all sent
     * successfully or all rolled back.</p>
     *
     * <p>Using KafkaTemplate can simplify the process of sending messages to a Kafka topic, and its integration with
     * Spring's transaction management support can help ensure data integrity and consistency.</p>
     *
     * <p>In summary, KafkaTemplate in Spring Kafka is a high-level abstraction over the KafkaProducer that simplifies
     * the process of sending messages to a Kafka topic. It provides a set of methods that handle the details of
     * creating a ProducerRecord and sending it to a topic, and it integrates with Spring's transaction management
     * support to ensure data integrity and consistency.</p>
     *
     * <p>KafkaTemplate will send data to brokers in this producer module</p>
     * @return
     */
    @Bean
    public KafkaTemplate<K, V> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
