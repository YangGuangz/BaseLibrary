package com.ygz.baselibrary.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Date: 2019/10/10 10:56
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class HttpURLConnectionUtils {

    /**
     * 根据url获取InputStream
     *
     * @param spec path
     * @return InputStream
     */
    private static InputStream getInputStream(String spec) {
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(spec);
            // 获得连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置超时时间5秒
            conn.setConnectTimeout(5000);
            // conn.setDoInput(true);
            // 不缓存
            // conn.setUseCaches(false);
            conn.connect();
            int respondCode = conn.getResponseCode();
            if (respondCode == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                return is;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭HttpURLConnection
            if (conn != null) {
                conn.disconnect();
            }
            // 关闭InputStream
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
