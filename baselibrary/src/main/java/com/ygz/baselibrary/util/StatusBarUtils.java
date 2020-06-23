package com.ygz.baselibrary.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

/**
 * @Date: 2019/10/10 15:26
 * @Author: YGZ
 * @Description: 状态栏工具类
 * @Version:
 */
public class StatusBarUtils {

    /**
     * 设置透明状态栏，支持4.4及以上版本
     *
     * @param activity Activity
     */
    @TargetApi(19)
    public static void translucentStatusBar(Activity activity) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 设置状态栏亮色模式（黑色字体图标），支持6.0及以上版本
     * 注意：
     * 在flyme上需要在设置里面打开沉浸式状态栏，否则在Activity 从onPause()再回到onResume()后会恢复dark模式；
     *
     * @param activity Activity
     */
    @TargetApi(23)
    public static void setMStatusBarLightMode(Activity activity) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decorView = activity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    /**
     * 设置状态栏颜色，支持6.0及以上版本
     *
     * @param activity Activity
     */
    @TargetApi(23)
    public static void setMStatusBarColor(Activity activity, int color) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = activity.getWindow();
                // 取消设置透明状态栏，使 ContentView 内容不再覆盖状态栏
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                // 设置状态栏颜色
                window.setStatusBarColor(ContextCompat.getColor(activity, color));
            }
        }
    }

}