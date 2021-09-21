package com.example.readwritesplitting1.controller;

import com.example.readwritesplitting1.entity.User;
import com.example.readwritesplitting1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @program: readwritesplitting1
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 11:26 PM
 */
@RestController
public class CustomerController {

    @Autowired
    private UserService userService;

    @GetMapping("all1")
    public List<User> getAllCustomer1() {
        return userService.getAllUserFromMaster();
    }

    @GetMapping("all2")
    public List<User> getAllCustomer2() {
        return userService.getAllUserFromSlave();
    }
}
