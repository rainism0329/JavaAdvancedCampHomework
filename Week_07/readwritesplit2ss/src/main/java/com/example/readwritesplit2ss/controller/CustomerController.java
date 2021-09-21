package com.example.readwritesplit2ss.controller;

import com.example.readwritesplit2ss.entity.User;
import com.example.readwritesplit2ss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @program: readwritesplit2ss
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/22 12:18 AM
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
