package utils;

import lombok.extern.java.Log;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @version 1.0
 * @program: insertDataTest
 * @description: TODO
 * @author: Philip.Zhang
 * @date: 2021/9/21 9:15 PM
 */
@Log
public class DataInsertTest {
    // 第一列为添加rewriteBatchedStatements=true前耗时
    // 第二列为添加rewriteBatchedStatements=true后耗时

    // time: 3302502ms = 3302s = 55min
    // time: 22118ms = 22s
    @Test
    public void batchInsertAutoCommit() {
        int batchCount = 1_000_000;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();

            ps = conn.prepareStatement("insert into userForWeek07(user_id, user_name, mobile, password) values(?, ?, ?, ?)");

            long start = System.currentTimeMillis();

            for(int i = 0; i < batchCount; i++){
                ps.setInt(1, i);
                ps.setString(2, "user_" + i);
                ps.setString(3,  String.valueOf(i));
                ps.setString(4,  String.valueOf(i));
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
            JdbcUtil.release(conn, ps,null);
        }
    }

    // time: 205210ms = 205s = 3m25s
    // time: 29963ms = 30s
    @Test
    public void batchInsertSingleTx() {
        int batchCount = 1_000_000;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();

            conn.setAutoCommit(false);

            ps = conn.prepareStatement("insert into userForWeek07(user_id, user_name, mobile, password) values(?, ?, ?, ?)");

            long start = System.currentTimeMillis();

            for(int i = 0; i < batchCount; i++) {
                ps.setInt(1, i);
                ps.setString(2, "user_" + i);
                ps.setString(3,  String.valueOf(i));
                ps.setString(4,  String.valueOf(i));
                ps.addBatch();
            }

            ps.executeBatch();
            ps.clearBatch();

            conn.commit();

            log.info("耗时：" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps,null);
        }
    }


    // time: 214829ms = 215s = 3m35s
    // time: 19730ms = 19s
    @Test
    public void batchInsertMultiTx() {
        int batchCount = 1_000_000;
        int txBatchCount = 10000;

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();

            conn.setAutoCommit(false);

            ps = conn.prepareStatement("insert into userForWeek07(user_id, user_name, mobile, password) values(?, ?, ?, ?)");

            long start = System.currentTimeMillis();

            for(int i = 1; i <= batchCount; i++) {
                ps.setInt(1, i);
                ps.setString(2, "user_" + i);
                ps.setString(3,  String.valueOf(i));
                ps.setString(4,  String.valueOf(i));
                ps.addBatch();

                if (i % txBatchCount == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                    conn.commit();
                }
            }

            log.info("耗时：" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps,null);
        }
    }


    // 100w拼接为一条sql
    // time: 18817ms = 19s
    // time: 19730ms = 20s
    @Test
    public void batchInsertConcatValueString() {
        int batchCount = 1_000_000;

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();

            conn.setAutoCommit(false);

            String insertSql = "insert into userForWeek07(user_id, user_name, mobile, password) values(?, ?, ?, ?)";

            StringBuilder stringBuilder = new StringBuilder(insertSql);

            long start = System.currentTimeMillis();

            for(int i = 1; i < batchCount; i++) {
                stringBuilder.append(",(?, ?, ?, ?)");
            }

            ps = conn.prepareStatement(stringBuilder.toString());

            for(int i = 1; i <= batchCount; i++) {
                ps.setInt((i - 1) * 4 + 1, i);
                ps.setString((i - 1) * 4 + 2, "user_" + i);
                ps.setString((i - 1) * 4 + 3,  String.valueOf(i));
                ps.setString((i - 1) * 4 + 4,  String.valueOf(i));
            }

            ps.execute();

            conn.commit();

            log.info("耗时：" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps,null);
        }
    }

    // 100w拼接为多条sql
    // time: 26432ms = 26s
    // time: 19730ms = 20s
    @Test
    public void batchInsertConcatValueString2() {
        int count = 1_000_000;
        int batchCount = 50000;

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();

            conn.setAutoCommit(false);

            String insertSql = "insert into userForWeek07(user_id, user_name, mobile, password) values";

            long start = System.currentTimeMillis();

            StringBuilder stringBuilder = new StringBuilder(insertSql);

            for(int i = 1; i <= count; i++) {
                stringBuilder.append("(?, ?, ?, ?),");

                if (i % batchCount == 0) {
                    String sql = stringBuilder.toString();
                    ps = conn.prepareStatement(sql.substring(0, sql.length() - 1));

                    for (int j = 1; j <= batchCount; j++) {
                        ps.setInt((j - 1) * 4 + 1, i);
                        ps.setString((j - 1) * 4 + 2, "user_" + i);
                        ps.setString((j - 1) * 4 + 3,  String.valueOf(i));
                        ps.setString((j - 1) * 4 + 4,  String.valueOf(i));
                    }
                    ps.execute();
                    conn.commit();

                    stringBuilder = new StringBuilder(insertSql);
                }
            }

            log.info("耗时：" + (System.currentTimeMillis() - start));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, ps,null);
        }
    }
}
