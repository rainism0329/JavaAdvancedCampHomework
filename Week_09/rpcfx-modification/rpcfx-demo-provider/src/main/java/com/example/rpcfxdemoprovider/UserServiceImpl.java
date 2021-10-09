package com.example.rpcfxdemoprovider;


import com.example.rpcfxdemoapi.User;
import com.example.rpcfxdemoapi.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
