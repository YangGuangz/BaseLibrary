package com.ygz.baselibrary.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * @Date: 2019/10/19 18:51
 * @Author: YGZ
 * @Description: Activity堆栈式管理
 * @Version:
 */
public class AppManager {

    private static AppManager sAppManager;

    private static volatile Stack<Activity> sActivityStack;

    private AppManager() {
        if (sActivityStack == null) {
            sActivityStack = new Stack<Activity>();
        }
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (sAppManager == null) {
            synchronized (AppManager.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManager();
                }
            }
        }
        return sAppManager;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity Activity
     */
    public void addActivity(Activity activity) {
        sActivityStack.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < sActivityStack.size(); i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }

    /**
     * 退出应用程序
     *
     * @param context Context
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Activity堆栈
     *
     * @return sActivityStack
     */
    public static Stack<Activity> getActivityStack() {
        return sActivityStack;
    }

    /**
     * 返回当前Activity栈中Activity的数量
     *
     * @return ActivityCount
     */
    public int getActivityCount() {
        return sActivityStack.size();
    }

    /**
     * 获取正在运行的Activity名称
     *
     * @param context Context
     * @return RunningActivityName
     */
    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null)
            return null;
        return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    }

}