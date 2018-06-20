package com.example.regex;

import org.junit.Test;

import java.util.regex.*;

import static org.junit.Assert.*;

public class RegExTests {

    public boolean matcher(String reg, String str) {
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(str);
        return matcher.find();
    }

    /**
     * 匹配数字
     */
    @Test
    public void numberTest() {
        String re1 = "^\\d$";//只能匹配一个数字
        String re2 = "^\\d{6}$";//匹配6位数
        String re3 = "^\\d{6,}$";//匹配至少6位数
        String re4 = "^\\d{7,8}$";//匹配7位到8位数，[0-9]{7,8}
        String re5 = "^[1-5]$";//匹配1到5之间的数字
        String re6 = "^(0|[1-9][0-9]*)$";//匹配0或非0开头的数字
        String re7 = "^[-+]?\\d+(\\.\\d+)?$";//只能输入实数
        String re8 = "^[0-9]+(.[0-9]{n})?";//只能输入n位小数的正实数
        String re9 = "";//

        String re10 = "";//
        String re11 = "";//

    }

    /**
     * 匹配字符
     */
    @Test
    public void charTest() {
        String re1 = "^(男|女)$";
        assertTrue(matcher(re1,"男"));
        assertTrue(matcher(re1,"女"));
        assertFalse(matcher(re1,"男女"));
        assertFalse(matcher(re1,"男女男"));

        String re2 = "^.{n}$";//只能输入n个字符
        String re3 = "^.[A-Za-z]+$";//只能输入英文字符
        String re4 = "^.[A-Za-z0-9]+$";//只能输入英文字符+数字
        String re5 = "^\\w+$";//只能输入英文字符/数字/下划线
        assertTrue(matcher(re5,"f3_fe2"));
        assertFalse(matcher(re5,"&dd3"));

        String re6 = "^[\\u4e00-\\u9fa5]{0,}$";//匹配只能是汉字的字符串
        String re7 = "^13[0-9]{1}[0-9]{8}|^15[9]{1}[0-9]{8}";//手机号
        String re8 = "^([a-zA-Z]\\:|\\\\)\\\\([^\\\\]+\\\\)*[^\\/:*?\"<>|]+\\.txt(l)?$";//验证文件扩展名为txt的文件路径
    }

}
