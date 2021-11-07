package com.example.producer.producer;

import com.example.producer.entity.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @version 1.0
 * @program: kafka-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/11/8 1:29 AM
 */
@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send(String str) {

        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(str+ UUID.randomUUID().toString());
        message.setSendTime(new Date());

        System.out.println("+++++++++++++++++++++  message = " + gson.toJson(message));

        kafkaTemplate.send("topic-idea-demo","syw", gson.toJson(message));
    }
}
