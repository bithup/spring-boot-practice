package com.example.guava;

import com.google.common.base.*;
import com.google.common.io.Files;
import com.google.common.primitives.Doubles;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class GuavaTests {

    /**
     * Strings类api测试
     */
    @Test
    public void StringsTests() {
        boolean a = Strings.isNullOrEmpty("");
    }

    @Test
    public void FilesTests() {

    }

    /**
     * 测试Double
     */
    @Test
    public void DoubleTests() {
        String s = "0.4333";
        Double d = Double.parseDouble(s);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(d));
        System.out.println(d);
    }

    /**
     * 时间戳测试
     */
    @Test
    public void timeStampTests() {
        //方法 一
        Long t1 = System.currentTimeMillis();
        //方法 二
        Long t2 = Calendar.getInstance().getTimeInMillis();
        //方法 三
        Long t3 = new Date().getTime();

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }
}
