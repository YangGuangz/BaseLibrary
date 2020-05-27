package com.egee.baselibrary.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;

import com.egee.baselibrary.R;

/**
 * @Date: 2019/10/19 14:43
 * @Author: YGZ
 * @Description: Activity跳转工具类
 * @Version:
 */
public class ActivityUtils {

    // TODO: 2020/4/11 判空

    /**
     * 显式跳转activity
     *
     * @param context Context
     * @param clazz   Class
     */
    public static void startActivity(Context context, @NonNull Class clazz) {
        startActivity(context, clazz, null);
    }

    /**
     * 隐式跳转activity
     *
     * @param context Context
     * @param action  Action
     */
    public static void startActivity(Context context, String action) {
        startActivity(context, action, null);
    }

    /**
     * 显式跳转activity，并携带bundle
     *
     * @param context Context
     * @param clazz   Class
     * @param bundle  Bundle
     */
    public static void startActivity(Context context, @NonNull Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 隐式跳转activity，并携带bundle
     *
     * @param context Context
     * @param action  Action
     * @param bundle  Bundle
     */
    public static void startActivity(Context context, String action, Bundle bundle) {
        Intent intent = new Intent();
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        intent.setAction(action);
        context.startActivity(intent);
    }

    /**
     * 有返回的显示跳转
     *
     * @param activity
     * @param clazz
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, @NonNull Class clazz, int requestCode) {
        startActivityForResult(activity, clazz, null, requestCode);
    }

    /**
     * 有返回的显示跳转，并携带bundle
     *
     * @param activity
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, @NonNull Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 有返回的隐式跳转
     *
     * @param activity    Activity
     * @param action
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, String action, int requestCode) {
        startActivityForResult(activity, action, null, requestCode);
    }


    /**
     * 有返回的隐式跳转，并携带bundle
     *
     * @param activity
     * @param action
     * @param bundle
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, String action, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        intent.setAction(action);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转到应用商店应用详情
     *
     * @param context     Context
     * @param packageName 包名
     */
    public static void openAppDetail(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String uriString = "market://details?id=" + packageName;
        intent.setData(Uri.parse(uriString));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 跳转到应用商店应用评论
     *
     * @param context     Context
     * @param packageName 包名
     */
    public static void openAppComment(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String uriString = "market://comments?id=" + packageName;
        intent.setData(Uri.parse(uriString));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 调用系统浏览器打开网页
     *
     * @param url 地址
     */
    public static void openUrlWithSystemBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 跳转到该应用的设置界面
     *
     * @param context Context
     */
    public static void openAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 打开网络设置界面，3.0以下打开设置界面
     *
     * @param context Context
     */
    public static void openWirelessSettings(Context context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                // 在Activity上下文之外调用startActivity需要FLAG_ACTIVITY_NEW_TASK属性
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } else {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }

    /**
     * 打开设置界面
     *
     * @param context Context
     */
    public static void openSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 携带号码跳转到拨号界面
     *
     * @param context
     * @param phoneNumber 手机号
     */
    public static void openDial(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * Bundle在Activity间传递数据，判断bundle不为空
     *
     * @param intent
     * @return
     */
    public static boolean bundleNotEmpty(Intent intent) {
        return intent != null && intent.getExtras() != null;
    }

    /**
     * 跳转到微信
     */
    public static boolean startWeChat(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
