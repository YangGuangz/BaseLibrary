package com.ygz.baselibrary.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import java.util.Locale;
import java.util.UUID;

/**
 * @Date: 2019/10/11 10:34
 * @Author: YGZ
 * @Description: 系统相关工具类
 * @Version:
 */
public class DeviceUtils {

    private static final String TAG = "DeviceUtils";

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统的API级别，数字表示
     *
     * @return
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 判断当前手机系统的API级别是否23及以上
     *
     * @return
     */
    public static boolean isSDKVersion23AndAbove() {
        return getSDKVersion() >= Build.VERSION_CODES.M;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机IMEI，需要
     * 1.android.permission.READ_PHONE_STATE 权限；
     * 2.系统版本api28及以下；
     *
     * @return 手机IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        String imei = SpUtils.getString(context, "imei");
        if (StringUtils.isEmpty(imei)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                // 检测权限
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (telephonyManager != null) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        // 8.0以下
                        imei = telephonyManager.getDeviceId();
                        SpUtils.saveString(context, "imei", imei);
                    } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                        // 9.0及以下
                        imei = telephonyManager.getImei();
                        SpUtils.saveString(context, "imei", imei);
                    }
                }
            }
        }
        LogUtils.d(TAG, "系统版本：api" + getSDKVersion() + "，imei：" + imei);
        return imei;
    }

    /**
     * 获取唯一标识符
     *
     * @return 唯一标识符
     */
    public static String getUid(Context context) {
        // 通过 SharedPreferences 获取 UID
        String uid = SpUtils.getString(context, "uid");
        if (StringUtils.notEmpty(uid)) {
            return uid;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 10.0及以上
            String androidId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if (StringUtils.notEmpty(androidId)) {
                // 通过 ANDROID_ID 生成 uid
                uid = androidId;
            } else {
                // 通过 UUID 生成 uid
                uid = UUID.randomUUID().toString();
            }
        } else {
            // 10.0以下，获取imei
            uid = getIMEI(context);
        }
        // 保存 uid 到 SharedPreferences
        SpUtils.saveString(context, "uid", uid);
        return uid;
    }

}