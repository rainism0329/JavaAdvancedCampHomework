package com.example.readwritesplitting1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @program: readwritesplitting1
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 11:35 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer id;

    private Integer userId;

    private String userName;
}
