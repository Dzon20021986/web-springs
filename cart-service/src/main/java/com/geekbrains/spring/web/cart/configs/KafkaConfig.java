package com.geekbrains.spring.web.cart.configs;


import com.geekbrains.spring.web.cart.dto.OrderDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableFeignClients
public class KafkaConfig {

    @Value("test-id")
    private String groupId;

    @Value("localhost:9092")
    private String server;

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        return props;
    }

    @Bean
    public JsonDeserializer jsonDeserializer(){
        JsonDeserializer jsonDeserializer = new JsonDeserializer();
        jsonDeserializer.addTrustedPackages("*");
        return jsonDeserializer;
    }

    @Bean
    public ProducerFactory<Long, OrderDto> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean(value = "KafkaTest")
    public KafkaTemplate<Long, OrderDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
