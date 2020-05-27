package com.egee.baselibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Date: 2019/10/10 10:35
 * @Author: YGZ
 * @Description: 网络工具类
 * @Version:
 */
public class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("NetworkUtils can't be instantiated!");
    }

    /**
     * 判断网络是否连接
     * <p>
     * 需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
     * </p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        // isConnected(): Indicates whether network connectivity exists and it is possible to establish connections and pass data
        // isAvailable(): Indicates whether network connectivity is possible
        return info != null && info.isConnected() && info.isAvailable();
    }

    /**
     * 网络未连接或不可用
     */
    public static boolean networknNotConnected(Context context) {
        return !isNetworkConnected(context);
    }

    /**
     * @param context Context
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi，net等连接的管理）
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        return cm != null ? cm.getActiveNetworkInfo() : null;
    }

    /**
     * 判断网络类型是否为wifi连接
     *
     * @return
     */
    public boolean isNetworkTypeWifi(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

}