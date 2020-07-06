package com.ygz.baselibrary.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/10/9 20:30
 * @Author: YGZ
 * @Description: Gson工具类
 * @Version:
 */
public class GsonUtils {

    private static Gson sGson = null;

    static {
        if (sGson == null) {
            sGson = new Gson();
        }
    }

    private GsonUtils() {
    }

    /**
     * Object转Json
     *
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj) {
        String json = null;
        if (sGson != null) {
            json = sGson.toJson(obj);
        }
        return json;
    }

    /**
     * Json转Bean
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> classOfT) {
        T t = null;
        if (sGson != null) {
            try {
                t = sGson.fromJson(json, classOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * Json转List
     * 泛型在编译期类型被擦除导致报错
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> List<T> json2List(String json, Class<T> classOfT) {
        List<T> list = null;
        if (sGson != null) {
            try {
                list = sGson.fromJson(json, new TypeToken<List<T>>() {
                }.getType());
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * Json转List
     * 解决泛型问题
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> classOfT) {
        List<T> list = new ArrayList<T>();
        if (sGson != null) {
            try {
                JsonArray jsonArray = new JsonParser().parse(json)
                        .getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    list.add(sGson.fromJson(jsonElement, classOfT));
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
