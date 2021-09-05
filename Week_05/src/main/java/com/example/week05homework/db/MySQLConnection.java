package com.example.week05homework.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @program: week05homework
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/5 5:21 PM
 */
public class MySQLConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/TestDB", "root", "03290329");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();
        if(connection == null) {
            return;
        }

        simpleJDBC(connection);
//        transactionJDBC(connection);
        connection.close();
    }

    //采用简单插入方法
    private static void simpleJDBC(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            //删除记录
            statement.executeUpdate("DELETE FROM USER");
            //增加记录
            statement.executeUpdate("INSERT INTO USER(ID,NAME,DESCRIPTION) VALUES('1','aaa','D1')");
            //查询记录
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM USER");
            resultSet.next();
            System.out.println(resultSet.getLong(1));
            resultSet.close();
            //更新记录
            statement.executeUpdate("UPDATE USER SET DESCRIPTION = 'Hello World!' WHERE ID = '1'");
            //删除记录
            statement.executeUpdate("DELETE FROM USER");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private static PreparedStatement preparedStatement;
    //采用批量方式插入
    private static void transactionJDBC(Connection connection) {
        try {
//            connection.setAutoCommit(false);
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER(ID,NAME,DESCRIPTION) VALUES(?,?,?)");
//            for( int i = 1; i < 10 ; i++){
//                preparedStatement.setString(1, String.valueOf(i));
//                preparedStatement.setString(2,"aaa"+i);
//                preparedStatement.setString(3,"D"+i);
//                preparedStatement.addBatch();
//            }
//            preparedStatement.executeBatch();
//            connection.commit();
//            connection.setAutoCommit(true);
            preparedStatement = connection.prepareStatement("SELECT * FROM USER");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> values = new HashMap<>();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
