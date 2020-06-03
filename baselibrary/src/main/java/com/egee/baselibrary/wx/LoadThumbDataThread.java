package com.egee.baselibrary.wx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.egee.baselibrary.util.BitmapUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Date: 2019/12/2 13:34
 * @Author: YGZ
 * @Description: 加载微信分享缩略图
 * @Version:
 */
public class LoadThumbDataThread extends Thread {

    private String mThumbDataUrl;
    private LoadThumbDataListener mListener;

    public LoadThumbDataThread(String thumbDataUrl, LoadThumbDataListener listener) {
        this.mThumbDataUrl = thumbDataUrl;
        this.mListener = listener;
    }

    @Override
    public void run() {
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        final int respondCode;
        try {
            URL url = new URL(mThumbDataUrl);
            // 获得连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置超时时间5秒
            conn.setConnectTimeout(5000);
            // conn.setDoInput(true);
            // 不缓存
            // conn.setUseCaches(false);
            conn.connect();
            respondCode = conn.getResponseCode();
            // 缩略图
            byte[] thumbData = null;
            if (respondCode == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                // 获取bitmap
                Bitmap bmp = BitmapFactory.decodeStream(is);
                /*// 压缩bitmap大小并把bitmap转换成ByteArray
                baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                LogUtils.d("原始分享缩略图大小 = " + baos.toByteArray().length / 1024 + "kb");
                int options = 90;
                // 微信分享图片大小限制32kb
                while (baos.toByteArray().length > 32 * 1024 && options != 10) {
                    // 清空baos
                    baos.reset();
                    // 这里压缩options%，把压缩后的数据存放到baos中
                    bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    options -= 10;
                }
                bmp.recycle();
                // 设置缩略图
                thumbData = baos.toByteArray();
                LogUtils.d("压缩后分享缩略图大小 = " + thumbData.length / 1024 + "kb");*/
                thumbData = BitmapUtils.bmpToByteArray(bmp, 32);
            }
            // 回调加载缩略图结果
            if (mListener != null)
                mListener.onLoadThumbData(thumbData);
        } catch (IOException e) {
            e.printStackTrace();
            // 回调加载缩略图结果
            if (mListener != null)
                mListener.onLoadThumbData(null);
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
     * 获取微信分享缩略图监听回调
     */
    public interface LoadThumbDataListener {
        void onLoadThumbData(byte[] thumbData);
    }

}