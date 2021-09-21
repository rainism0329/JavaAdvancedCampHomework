package com.example.readwritesplit2ss.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @version 1.0
 * @program: readwritesplit2ss
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/22 12:19 AM
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
