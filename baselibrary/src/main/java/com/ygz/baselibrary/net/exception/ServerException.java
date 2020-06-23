package com.ygz.baselibrary.net.exception;

/**
 * @Date: 2019/10/18 10:21
 * @Author: YGZ
 * @Description: 服务器层异常类，根据与服务器层约定好的状态码定义
 * @Version:
 */
public class ServerException extends RuntimeException {

    public int code;

    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}