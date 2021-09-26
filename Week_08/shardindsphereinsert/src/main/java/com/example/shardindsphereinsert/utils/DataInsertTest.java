package com.example.shardindsphereinsert.utils;

import lombok.extern.java.Log;
import org.junit.Test;

import java.sql.*;

/**
 * @version 1.0
 * @program: shardindsphereinsert
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/26 8:39 PM
 */
@Log
public class DataInsertTest {
    @Test
    public void batchInsertAutoCommit() {
        int batchCount = 100;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement("insert into t_order(user_id, status) values(?, ?)");

            long start = System.currentTimeMillis();

            for(int i = 0; i < batchCount; i++){
                ps.setInt(1, i);
                ps.setString(2, "ok");
                ps.addBatch();

                if (i % 1000 == 0) {
                    log.info("count：" + i);
                }
            }

            ps.executeBatch();
            ps.clearBatch();

            log.info("耗时：" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps,null);
        }
    }

    @Test
    public void test() throws SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/sharding_db", "root", "root");
            st = con.createStatement();

            rs = st.executeQuery("select * from t_order");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "|" + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(con,st,rs);
        }
    }

    @Test
    public void testRemove()  {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/sharding_db", "root", "root");
            st = con.createStatement();

            st.executeUpdate("delete from t_order");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(con,st,rs);
        }
    }
}
