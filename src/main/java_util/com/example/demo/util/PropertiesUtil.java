package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    /**
     * 通过ClassLoader读取properties文件，只能读取classpath下的文件
     * @param filePath properties相对路径
     * @return Properties对象
     *
     */
    public static Properties getProperties(String filePath) {
        Properties p = new Properties();
        //此处不要使用Object.class.getClassLoader()
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
}
