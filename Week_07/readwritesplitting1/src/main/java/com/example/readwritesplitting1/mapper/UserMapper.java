package com.example.readwritesplitting1.mapper;

import com.example.readwritesplitting1.datasource.CurDataSource;
import com.example.readwritesplitting1.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0
 * @program: readwritesplitting1
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 11:36 PM
 */
@Mapper
public interface UserMapper {

    @CurDataSource
    @Select("select id, user_id userId, user_name userName from user")
    List<User> findAllMsater();

    @CurDataSource("slave")
    @Select("select id, user_id userId, user_name userName from user")
    List<User> findAllSlave();

}