package com.egee.baselibrary.net.exception;

/**
 * @Date: 2019/10/18 10:20
 * @Author: YGZ
 * @Description: 网络请求统一异常类
 * @Version:
 */
public class ResponseException extends Exception {

    private int code;

    private String message;

    public ResponseException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}