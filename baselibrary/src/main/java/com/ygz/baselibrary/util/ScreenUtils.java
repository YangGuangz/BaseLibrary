package com.ygz.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * @Date: 2019/10/10 11:50
 * @Author: YGZ
 * @Description: 屏幕工具类
 * @Version:
 */
public class ScreenUtils {

    /**
     * 获得屏幕宽度像素值
     *
     * @param context Context
     * @return 屏幕宽度像素值
     */
    public static int getScreenWidth(Context context) {
        int widthPixels = 0;
        if (context != null) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(displayMetrics);
                widthPixels = displayMetrics.widthPixels;
            }
        }
        return widthPixels;
    }

    /**
     * 获得屏幕高度像素值
     *
     * @param context Context
     * @return 屏幕高度像素值
     */
    public static int getScreenHeight(Context context) {
        int heightPixels = 0;
        if (context != null) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(displayMetrics);
                heightPixels = displayMetrics.heightPixels;
            }
        }
        return heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param activity activity
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Activity activity) {
        int statusBarHeight = 0;
        try {
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        int result = 0;
        try {
            int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = Resources.getSystem().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 反射方式获取状态栏高度
     *
     * @param context Context
     * @return 状态栏高度
     */
    private static int getStatusBarHeightByReflect(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int id = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity Activity
     * @return 屏幕截图Bitmap
     */
    public static Bitmap getScreenShot(Activity activity) {
        if (activity == null) {
            return null;
        }
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity Activity
     * @return 屏幕截图Bitmap
     */
    public static Bitmap getScreenShotWithoutStatusBar(Activity activity) {
        if (activity == null) {
            return null;
        }
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

}
