package com.egee.baselibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * @Date: 2020/4/16 9:34
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class BaseApplication extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mAppContext;
    }

}
