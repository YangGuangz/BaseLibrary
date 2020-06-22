package com.egee.baselibrary.util;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @Date: 2019/10/10 10:20
 * @Author: YGZ
 * @Description: 基于Logger封装的日志打印工具类
 * @Version:
 */
public class LogUtils {


    private LogUtils() {
        throw new UnsupportedOperationException("LogUtils cannot be instantiated!");
    }

    public static void init(final boolean isLoggable) {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                // 是否打印日志
                return isLoggable;
            }
        });
    }

    /**
     * VERBOSE级别
     *
     * @param msg
     */
    public static void v(String msg) {
        Logger.v(msg);
    }

    public static void v(String tag, String msg) {
        Logger.t(tag).v(msg);
    }

    /**
     * DEBUG级别，打印对象
     *
     * @param obj
     */
    public static void d(Object obj) {
        Logger.d(obj);
    }

    public static void d(String tag, Object obj) {
        Logger.t(tag).d(obj);
    }

    /**
     * DEBUG级别
     *
     * @param msg
     */
    public static void d(String msg) {
        Logger.d(msg);
    }

    /**
     * DEBUG级别
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        Logger.t(tag).d(msg);
    }

    /**
     * INFO级别
     *
     * @param msg
     */
    public static void i(String msg) {
        Logger.i(msg);
    }

    /**
     * INFO级别
     *
     * @param msg
     */
    public static void i(String tag, String msg) {
        Logger.t(tag).i(msg);
    }

    /**
     * WARN级别
     *
     * @param msg
     */
    public static void w(String msg) {
        Logger.w(msg);
    }

    public static void w(String tag, String msg) {
        Logger.t(tag).w(msg);
    }


    /**
     * ERROR级别
     *
     * @param msg
     */
    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void e(String tag, String msg) {
        Logger.t(tag).e(msg);
    }

    /**
     * ERROR级别
     *
     * @param throwable
     * @param msg
     */
    public static void e(Throwable throwable, String msg) {
        Logger.e(throwable, msg);
    }

    public static void e(String tag, Throwable throwable, String msg) {
        Logger.t(tag).e(throwable, msg);
    }

    /**
     * ASSERT级别
     *
     * @param msg
     */
    public static void a(String msg) {
        Logger.wtf(msg);
    }

    public static void a(String tag, String msg) {
        Logger.t(tag).wtf(msg);
    }

    /**
     * 打印xml
     *
     * @param xml
     */
    public static void xml(String xml) {
        Logger.xml(xml);
    }

    public static void xml(String tag, String xml) {
        Logger.t(tag).xml(xml);
    }

    /**
     * 打印json
     *
     * @param json
     */
    public static void json(String json) {
        Logger.json(json);
    }

    public static void json(String tag, String json) {
        Logger.t(tag).json(json);
    }

}