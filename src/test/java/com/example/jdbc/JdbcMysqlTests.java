package com.example.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class JdbcMysqlTests {

    static final String DB_URL = "jdbc:mysql://122.114.4.134:13306/xghmall?useUnicode=true&characterEncoding=utf-8";
    static final String USER = "root";
    static final String PASS = "xghwep999xgh";
    static Connection conn = null;
    //Statement对象执行SQL不能接收参数
    static Statement stat = null;
    //PreparedStatement对象能接收参数，创建时与SQL绑定，只能执行同一SQL不同参数的语句
    static PreparedStatement pstat = null;
    //CallableStatement对象用来调用存储过程
    static CallableStatement cstat = null;
    static ResultSet rs = null;
    static String sql = null;

    @Before
    public void getConnect() throws ClassNotFoundException, SQLException {
        //注册驱动，jdbc4可以自动注册，
        //还可通过DriverManager.registerDriver();方法注册，用于其他jvm
        Class.forName("com.mysql.jdbc.Driver");
        //如果载入了多种数据库驱动，DriverManager是如何知道使用哪个驱动的
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stat = conn.createStatement();
    }

    @After
    public void closeConnect() throws SQLException {
        //关闭资源的顺序与创建获取的顺序相反，必须吗？为什么？
        if (rs != null) {
            rs.close();
        }
        if (stat != null) {
            stat.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 无参 读操作
     * @throws SQLException 查询异常
     */
    @Test
    public void test1() throws SQLException {
        sql = "SELECT id,account,is_verify,create_date FROM tb_member_user";
        rs = stat.executeQuery(sql);
        while (rs.next()) {
            Long id = rs.getLong("id");
            String account = rs.getString("account");
            Integer is_verify = rs.getInt("is_verify");
            Date create_date = rs.getDate("create_date");
            System.out.println(id+"--"+account+"--"+is_verify+"--"+create_date);
        }
        //一个Statement对象能执行多条不同SQL
        sql = "SELECT uuid FROM tb_member_user WHERE id = 2";
        rs = stat.executeQuery(sql);
        while (rs.next()) {
            String uuid = rs.getString("uuid");
            System.out.println(uuid);
        }
    }

    /**
     * 无参 写操作
     */
    @Test
    public void test2() throws SQLException {
        sql = "INSERT INTO tb_member_user (account,create_date) VALUES (18337173119,STR_TO_DATE('2018-06-05','%Y-%m-%d'))";
        int flag = stat.executeUpdate(sql);
        System.out.println(flag);
    }

    /**
     * 有 参数查询
     *
     */
    @Test
    public void test3() throws SQLException {
        sql = "SELECT account,is_verify,create_date FROM tb_member_user WHERE id = ? ";
        pstat = conn.prepareStatement(sql);
        pstat.setLong(1,1);
        //和Statement的区别,注意pstat.executeQuery(sql)这样写运行时会报sql语法错误,
        //但是IDEA不提示错误，是因为，pstat继承自stat,实际调用的是Statement的方法
        rs = pstat.executeQuery();
        while (rs.next()) {
            String account = rs.getString("account");
            System.out.println(account);
        }
    }


    /**
     * jdbc使用索引、视图、存储过程等
     */
    @Test
    public void test4() throws SQLException {
        sql = "{call proc_name(?,?)}";
        cstat = conn.prepareCall(sql);
    }
}
