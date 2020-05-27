package com.egee.baselibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @Date: 2019/10/10 14:35
 * @Author: YGZ
 * @Description: SharedPreferences工具类
 * @Version:
 */
@SuppressLint("ApplySharedPref")
public class SpUtils {

    private static final String SP_FILE_NAME = "Preferences";

    private static SharedPreferences mSp;

    public static void saveLong(Context context, String key, long value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        mSp.edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String key, long value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return mSp.getLong(key, value);
    }


    public static void saveBoolean(Context context, String key, boolean value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        mSp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return mSp.getBoolean(key, defValue);
    }

    public static void saveString(Context context, String key, String value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putString(key, value).commit();
    }

    public static void removeString(Context context, String key) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().remove(key).commit();
    }

    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public static String getString(Context context, String key, String defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return mSp.getString(key, defValue);
    }

    public static void saveInt(Context context, String key, int value) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        mSp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return mSp.getInt(key, defValue);
    }

    public static void clear(Context context) {
        if (mSp == null)
            mSp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        mSp.edit().clear().commit();
    }

}
