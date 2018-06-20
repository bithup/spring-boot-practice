package com.example.demo.util;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

@Log4j2
public class JarUtil {
    /**
     * 查询jar包运行路径
     *
     * @return
     */
    public static String getJarUrl() {
        URL url = JarUtil.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(url.getPath());
        String filePath = "";
        try {
            // 转化为utf-8编码，支持中文
            filePath = URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            log.info(e.getMessage(), e);
        }

        int i = filePath.indexOf(".jar");
        // 可执行jar包运行的结果里包含".jar"
        if (i >= 0) {
            // 获取jar包所在目录
            filePath = filePath.substring(0, filePath.lastIndexOf("/", i) + 1);
            System.out.println(filePath);
        }

        File file = new File(filePath);
        filePath = file.getAbsolutePath();

        i = filePath.indexOf("file:");
        if (i >= 0) {
            filePath = filePath.substring(0, filePath.lastIndexOf(File.separator, i));
        }
//        String classesUrl = File.separator + "classes";
//        i = filePath.indexOf(classesUrl);
//        if (i >= 0) {
//            filePath = filePath.substring(0, i);
//        }

        return filePath;
    }// getJarUrl定义结束

    /**
     * 首先从jar目录下读取文件，如果不存在在当做classpath从jar内部读取。
     *
     * @param path
     * @return
     */
    public static InputStream getFileStream(String path) {
        String jarPath = JarUtil.getJarUrl() + File.separator + path;
        InputStream in = null;
        try {
            in = new FileInputStream(jarPath);
            if (in != null){
                return in;
            }
        } catch (FileNotFoundException e) {
            log.info(e.toString(),e);
        }

        in = JarUtil.class.getClassLoader().getResourceAsStream(path);
        return in;
    }
}
