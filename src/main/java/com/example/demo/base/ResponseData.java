package com.example.demo.base;

import springfox.documentation.annotations.ApiIgnore;

/**
 * REST API 响应数据结构
 */
@ApiIgnore
public class ResponseData {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public String flag;
    public String message;
    public Object data;

    public ResponseData(String flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public ResponseData(String flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
