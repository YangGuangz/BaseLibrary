package com.egee.baselibrary.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019/10/10 11:35
 * @Author: YGZ
 * @Description: 包工具类
 * @Version:
 */
public class PackageUtils {

    /**
     * 获取包名
     *
     * @param context Context
     * @return 包名
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取版本名
     *
     * @param context Context
     * @return 版本名
     */
    public static String getVersionName(Context context) {
        // 包管理器
        PackageManager packageManager = context.getPackageManager();
        // 参数一：包名；参数二：标记，获取什么数据就给什么标记，给0，只获取最基本信息。
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(context), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取版本号
     *
     * @param context Context
     * @return 版本号
     */
    public static int getVersionCode(Context context) {
        // 包管理器
        PackageManager packageManager = context.getPackageManager();
        // 参数一：包名；参数二：标记，获取什么数据就给什么标记，给0，只获取最基本信息。
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(context), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取手机内非系统预装应用
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getInstalledPackages(Context context) {
        List<PackageInfo> installedPackagesResult = new ArrayList<PackageInfo>();
        PackageManager packageManager = context.getPackageManager();
        // 获取手机内所有已安装应用
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
            // 判断是否为非系统预装的应用程序
            /*if ((packageInfo.applicationInfo.flags & packageInfo.applicationInfo.FLAG_SYSTEM) != 0) {
                // 非系统预装的应用程序
            }*/
            installedPackagesResult.add(packageInfo);
        }
        return installedPackagesResult;
    }

    /**
     * 根据包名判断应用是否安装
     *
     * @param context
     * @param packageName 包名
     * @return
     */
    public static boolean isPackageInstalledByGetInfo(Context context, String packageName) {
        /**
         * 通过包名获取app的信息，当app不存在时，
         * 会抛出NameNotFoundException 异常，
         * 通过对异常的捕获，从而判断是否安装了该应用
         */
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo(packageName, PackageManager.GET_GIDS);
            LogUtils.d(packageName + " 已安装");
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // catch找不到的异常，说明没有安装该app
            LogUtils.d(packageName + " 未安装");
            return false;
        }
    }

    /**
     * 根据包名判断应用是否安装
     *
     * @param context
     * @param packageName 包名
     * @return
     */
    @Deprecated
    private static boolean isPackageInstalled(Context context, String packageName) {
        /**
         * 通过遍历获取到的已安装app包信息，匹配包名判断
         */
        List<PackageInfo> installedPackages = getInstalledPackages(context);
        for (PackageInfo packageInfo : installedPackages) {
            if (packageInfo.packageName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

}