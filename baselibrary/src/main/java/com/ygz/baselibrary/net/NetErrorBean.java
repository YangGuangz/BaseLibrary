package com.ygz.baselibrary.net;

/**
 * @Date: 2019/10/17 11:45
 * @Author: YGZ
 * @Description: 网络请求异常bean类
 * @Version:
 */
public class NetErrorBean {

    private int code;

    private String message;

    public NetErrorBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NetErrorBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
