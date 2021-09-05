package com.example.week05homework.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/5 6:50 PM
 */
public class JDBCHikariDemo {

    public static void hikariTest(){
        HikariConfig config = new HikariConfig("src/main/resources/hikari.properties");
        HikariDataSource ds = new HikariDataSource(config);
        try {
            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("user{id:"+rs.getInt("id")+",name:"+rs.getString("name")+"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ds.close();
        }
    }

    public static void main(String[] args) {
        hikariTest();
    }
}