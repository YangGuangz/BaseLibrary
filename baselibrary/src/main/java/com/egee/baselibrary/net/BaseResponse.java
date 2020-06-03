package com.egee.baselibrary.net;

/**
 * @Date: 2019/10/18 13:02
 * @Author: YGZ
 * @Description: 请求结果统一格式封装
 * @Version:
 */
public class BaseResponse<T> {

    /**
     * {
     * "code": 200,
     * "message": "操作成功",
     * "data": {"token": "Bearer a9b3f53a3d817259218ba8023be4ccd9"}
     * }
     */

    private int code;

    public String message;

    public T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
