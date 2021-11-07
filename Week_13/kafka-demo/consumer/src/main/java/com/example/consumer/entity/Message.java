package com.example.consumer.entity;

import lombok.Data;

import java.util.Date;

/**
 * @version 1.0
 * @program: kafka-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/11/8 1:04 AM
 */
@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}
