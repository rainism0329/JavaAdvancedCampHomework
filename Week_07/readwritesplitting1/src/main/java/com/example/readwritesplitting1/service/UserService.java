package com.example.readwritesplitting1.service;

import com.example.readwritesplitting1.entity.User;
import com.example.readwritesplitting1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @program: readwritesplitting1
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 11:42 PM
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUserFromMaster() {
        return userMapper.findAllMsater();
    }

    public List<User> getAllUserFromSlave() {
        return userMapper.findAllSlave();
    }
}