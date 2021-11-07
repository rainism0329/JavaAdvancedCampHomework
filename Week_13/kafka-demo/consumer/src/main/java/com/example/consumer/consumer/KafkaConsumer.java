package com.example.consumer.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @version 1.0
 * @program: kafka-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/11/8 1:03 AM
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"topic-idea-demo"})
    public void consumer(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("----------------- record =" + record);
            System.out.println("------------------ message =" + message);
        }
    }
}
