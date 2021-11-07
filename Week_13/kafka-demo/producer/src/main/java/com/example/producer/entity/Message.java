package com.example.producer.entity;

import lombok.Data;

import java.util.Date;

/**
 * @version 1.0
 * @program: kafka-demo
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/11/8 1:28 AM
 */
@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}