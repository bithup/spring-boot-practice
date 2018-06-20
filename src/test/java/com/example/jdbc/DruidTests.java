package com.example.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidTests {

    /*
     * 自己实现基本的连接池，实现javax.sql.DataSource接口
     * Druid监控连接池性能，插件扩展
     * https://www.cnblogs.com/xdp-gacl/p/4002804.html
     * https://blog.csdn.net/gaoyuliu/article/details/79730969
     * 用日志文件记录一些重要的写SQL
     * 数据库连接加密
     * https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
     */

    public Properties getProp(String filePath) {
        Properties p = new Properties();
        InputStream is = DruidTests.class.getClassLoader().getResourceAsStream(filePath);
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public DruidDataSource getDataSource() {
        DruidDataSource dataSource = null;
        Properties druid_config = getProp("druid_config.properties");
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(druid_config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Test
    public void druidTests() throws SQLException {
        DruidPooledConnection dpc = null;
        Statement stat;
        ResultSet rs;
        try {
            dpc = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stat = dpc.createStatement();
        rs = stat.executeQuery("SELECT * FROM tb_member_user");
        int columns = rs.getMetaData().getColumnCount();
        System.out.println(columns);
    }

}
