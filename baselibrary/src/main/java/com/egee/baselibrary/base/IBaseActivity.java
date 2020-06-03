package com.egee.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * @Date: 2019/10/16 14:25
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public interface IBaseActivity extends IBaseView {

    void onFront();

    void onBackground();

    void initView();

    void setStatusBarHeight(int statusBarResId);

    void setBack(int backResId);

    void initData(Bundle savedInstanceState);

    void setActionBarTitle(int titleStringResId);

    void setActionBarTitle(String titleString);

    void setActionBarTitle(int titleViewResId, int titleStringResId);

    void setActionBarTitle(int titleViewResId, String titleString);

    void reload();

    boolean isTranslucentStatusBar();

    void translucentStatusBar();

    boolean isStatusBarLightMode();

    void statusBarLightMode();

    void setStatusBarColor(int color);

    boolean isAactivityAnimation();

    boolean isDetectVersionUpdate();

    void startActivity(@NonNull Class clazz);

    void startActivity(@NonNull Class clazz, Bundle bundle);

    void startActivityForResult(@NonNull Class clazz, int requestCode);

    void startActivityForResult(@NonNull Class clazz, Bundle bundle, int requestCode);

    boolean isShowFloating();

}