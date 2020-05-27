package com.egee.baselibrary.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Date: 2019/12/17 21:41
 * @Author: YGZ
 * @Description: 获取图片bitmap工具类
 * @Version:
 */
public class LoadBitmapThread extends Thread {

    private String mUrl;
    private LoadBitmapListener mListener;

    public LoadBitmapThread(String url, LoadBitmapListener listener) {
        this.mUrl = url;
        this.mListener = listener;
    }

    @Override
    public void run() {
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        final int respondCode;
        try {
            URL url = new URL(mUrl);
            // 获得连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置超时时间5秒
            conn.setConnectTimeout(5000);
            // conn.setDoInput(true);
            // 不缓存
            // conn.setUseCaches(false);
            conn.connect();
            respondCode = conn.getResponseCode();
            if (respondCode == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                // 获取图片bitmap
                Bitmap bmp = BitmapFactory.decodeStream(is);
                if (mListener != null) {
                    mListener.onLoadBitmap(bmp != null, bmp);
                }
            } else {
                if (mListener != null)
                    mListener.onLoadBitmap(false, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (mListener != null)
                mListener.onLoadBitmap(false, null);
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
            // 关闭ByteArrayOutputStream
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取图片bitmap
     */
    public interface LoadBitmapListener {
        void onLoadBitmap(boolean isSuccess, Bitmap bitmap);
    }

}