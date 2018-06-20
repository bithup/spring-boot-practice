package com.example.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {

    public static boolean matcher(String reg, String str) {
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(str);
        return matcher.find();
    }
}
