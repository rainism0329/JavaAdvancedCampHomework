package com.example.readwritesplit2ss.mapper;

import com.example.readwritesplit2ss.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0
 * @program: readwritesplit2ss
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/22 12:20 AM
 */
@Mapper
public interface UserMapper {

    @Select("select id, user_id userId, user_name userName from user")
    List<User> findAllMsater();

    @Select("select id, user_id userId, user_name userName from user")
    List<User> findAllSlave();

}