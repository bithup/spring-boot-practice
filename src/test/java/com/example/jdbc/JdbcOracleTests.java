package com.example.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class JdbcOracleTests {

    //url中最后的是server name/SID，与Oracle服务名OracleServiceXE后面一致
    //使用自己创建的用户kevin登录会出现表或视图不存在错误，可能是权限问题，改用scott用户
    //scott用户的默认密码是tiger
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER = "scott";
    static final String PASS = "tiger";
    static Connection conn = null;
    static Statement stat = null;
    static PreparedStatement pstat = null;
    static ResultSet rs = null;
    static String sql = null;

    @Before
    public void getConnect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stat = conn.createStatement();
    }

    @After
    public void closeConnect() throws SQLException {
        rs.close();
        stat.close();
        conn.close();
    }

    @Test
    public void test1() throws SQLException {
        sql = "SELECT * FROM STUDENT";
        rs = stat.executeQuery(sql);
        while (rs.next()) {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            System.out.println(id + " " + name);
        }
    }
}
