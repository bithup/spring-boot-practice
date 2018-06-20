package com.example.demo.base;

/**
 * 基础控制器，包含公用方法
 */
public abstract class BaseController {

    /**
     * 请求成功，返回flag和message
     *
     * @return
     */
    protected ResponseData successData() {
        return new ResponseData(ResponseData.SUCCESS, "操作成功");
    }

    /**
     * 请求成功，返回flag和message
     * @return
     */
    protected ResponseData successData(String message) {
        return new ResponseData(ResponseData.SUCCESS, message);
    }

    /**
     * 请求成功，返回flag、message和data
     * @return
     */
    protected ResponseData successData(String message, Object data) {
        return new ResponseData(ResponseData.SUCCESS, message ,data);
    }

    /**
     * 请求失败，返回flag和message
     *
     * @return
     */
    protected ResponseData failureData() {
        return new ResponseData(ResponseData.FAILURE, "操作失败");
    }

    /**
     * 请求失败，返回flag和message
     * @return
     */
    protected ResponseData failureData(String message) {
        return new ResponseData(ResponseData.FAILURE, message);
    }
}
