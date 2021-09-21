package com.example.readwritesplit2ss.service;

import com.example.readwritesplit2ss.entity.User;
import com.example.readwritesplit2ss.mapper.UserMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @program: readwritesplit2ss
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/22 12:28 AM
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUserFromMaster() {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        List<User> users = userMapper.findAllMsater();
        hintManager.close();
        return users;
    }

    public List<User> getAllUserFromSlave() {
        return userMapper.findAllSlave();
    }
}