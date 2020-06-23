package com.ygz.baselibrary.net.exception;

/**
 * @Date: 2019/10/18 11:16
 * @Author: YGZ
 * @Description: 服务器层约定的状态码
 * @Version:
 */
public class ServerCode {

    /**
     * 操作成功
     */
    public static final int SERVER_CODE_SUCCESS = 200;
    /**
     * 操作失败
     */
    public static final int SERVER_CODE_FAILURE = 400;
    /**
     * 授权过期（Token失效），请重新登录
     */
    public static final int SERVER_CODE_TOKEN_INVALID = 401;
    /**
     * 访问失败，没有授权，请重新登录授权
     */
    public static final int SERVER_CODE_NO_TOKEN = 403;
    /**
     * 请求参数错误
     */
    public static final int SERVER_CODE_PARAMETER_ERROR = 412;
    /**
     * 系统繁忙，请稍后重试
     */
    public static final int SERVER_CODE_OTHER = 500;

}
