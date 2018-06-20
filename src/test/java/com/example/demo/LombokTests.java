package com.example.demo;

import com.example.demo.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LombokTests {

    private User user;

    @Before
    public void setUser() {
        user = new User();
        user.setTelephone("18337173119");
        user.setPassword("123456");
    }

    @After
    public void afterTest() {

    }

    @Test
    public void test() {
        /*
        lombok在jdk9以上版本下运行不起作用
         */
        System.out.println(user.getTelephone());
    }
}
