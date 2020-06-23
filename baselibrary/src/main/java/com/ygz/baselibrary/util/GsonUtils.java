package com.ygz.baselibrary.util;

import com.google.gson.Gson;

/**
 * @Date: 2019/10/9 20:30
 * @Author: YGZ
 * @Description: Gson工具类
 * @Version:
 */
public class GsonUtils {

    /**
     * Json String转Bean
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> classOfT) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object转Json String
     *
     * @param src
     * @return
     */
    public static String obj2Json(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

}
