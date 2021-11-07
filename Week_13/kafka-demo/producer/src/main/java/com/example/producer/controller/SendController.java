package com.example.producer.controller;

import com.example.producer.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @program: kafka-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/11/8 1:26 AM
 */
@RestController
@RequestMapping("/test")
public class SendController {

    @Autowired
    private Producer producer;

    @PostMapping("/send")
    public void send() {
        producer.send("phil sent");
    }
}

