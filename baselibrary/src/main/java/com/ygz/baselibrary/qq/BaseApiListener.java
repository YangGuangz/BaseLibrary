package com.ygz.baselibrary.qq;

import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

/**
 * @Date: 2019/10/11 11:10
 * @Author: YGZ
 * @Description: 实现回调IRequestListener，使用requestAsync、request等通用方法调用sdk未封装的接口时，例如上传图片、查看相册等，需传入该回调的实例。
 * @Version:
 */
public class BaseApiListener implements IRequestListener {

    @Override
    public void onComplete(JSONObject jsonObject) {

    }

    @Override
    public void onIOException(IOException e) {

    }

    @Override
    public void onMalformedURLException(MalformedURLException e) {

    }

    @Override
    public void onJSONException(JSONException e) {

    }

    @Override
    public void onConnectTimeoutException(ConnectTimeoutException e) {

    }

    @Override
    public void onSocketTimeoutException(SocketTimeoutException e) {

    }

    /**
     * 1.4版本中IRequestListener 新增两个异常，当前网络不可用时触发此异常
     */
    @Override
    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {

    }

    /**
     * http请求返回码非200时触发此异常
     */
    @Override
    public void onHttpStatusException(HttpUtils.HttpStatusException e) {

    }

    /**
     * 出现未知错误时会触发此异常
     */
    @Override
    public void onUnknowException(Exception e) {

    }

}