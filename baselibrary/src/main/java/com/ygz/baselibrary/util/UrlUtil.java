package com.ygz.baselibrary.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2020/2/25 14:22
 * @Author: YGZ
 * @Description: url工具类
 * @Version:
 */
public class UrlUtil {

    public static class UrlEntity {
        /**
         * 基础url
         */
        public String baseUrl;
        /**
         * url参数
         */
        public Map<String, String> params;
    }

    /**
     * 解析url
     *
     * @param url
     * @return
     */
    public static UrlEntity parse(String url) {
        UrlEntity entity = new UrlEntity();
        if (url == null) {
            return entity;
        }
        url = url.trim();
        if (url.equals("")) {
            return entity;
        }
        try {
            String[] urlParts = url.split("\\?");
            entity.baseUrl = urlParts[0];
            // 没有参数
            if (urlParts.length == 1) {
                return entity;
            }
            // 有参数
            String[] params = urlParts[1].split("&");
            entity.params = new HashMap<>();
            for (String param : params) {
                String[] keyValue = param.split("=");
                entity.params.put(keyValue[0], keyValue[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

}