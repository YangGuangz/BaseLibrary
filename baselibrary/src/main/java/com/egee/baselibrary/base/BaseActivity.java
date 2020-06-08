package com.egee.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.egee.baselibrary.R;
import com.egee.baselibrary.dialog.LoadingDialog;
import com.egee.baselibrary.update.CustomUpdateStrategy;
import com.egee.baselibrary.update.DefaultDownloadNotifier;
import com.egee.baselibrary.update.DefaultFileCreator;
import com.egee.baselibrary.update.DefaultInstallNotifier;
import com.egee.baselibrary.update.DefaultUpdateNotifier;
import com.egee.baselibrary.util.ActivityUtils;
import com.egee.baselibrary.util.AppManager;
import com.egee.baselibrary.util.FixMemLeak;
import com.egee.baselibrary.util.InputMethodUtils;
import com.egee.baselibrary.util.ScreenUtils;
import com.egee.baselibrary.util.StatusBarUtils;
import com.egee.baselibrary.util.ToastUtil;
import com.egee.baselibrary.util.ViewUtils;

import org.jetbrains.annotations.NotNull;
import org.lzh.framework.updatepluginlib.UpdateBuilder;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @Date: 2019/10/16 14:19
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public abstract class BaseActivity extends SupportActivity implements IBaseActivity {

    protected Activity mContext;
    protected LoadingDialog mLoadingDialog;
    private Unbinder mUnbinder;
    // 打开的Activity数量统计
    private int mActivityStartCount = 0;
    // 分页加载当前页数，默认第一页
    protected int mPage = 1;
    // 分页加载每页条数，默认每页15条
    protected int mPerPage = 15;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // Activity转场动画
        if (isAactivityAnimation()) {
            overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        }
        if (getLayoutResID() > 0) {
            // 设置透明状态栏
            translucentStatusBar();
            // 状态栏亮色模式
            statusBarLightMode();
            // 设置布局文件id
            setContentView(getLayoutResID());
            // setContentView(getLayoutResID());
            // 绑定ButterKnife
            mUnbinder = ButterKnife.bind(this);
            // 初始化布局
            initView();
        }
        // 初始化数据
        initData(savedInstanceState);
        // 添加到Activity堆栈
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityStartCount++;
        // 数值从0变到1说明是从后台切到前台
        if (mActivityStartCount == 1) {
            // 从后台切到前台
            onFront();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityStartCount--;
        // 数值从1到0说明是从前台切到后台
        if (mActivityStartCount == 0) {
            // 从前台切到后台
            onBackground();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑ButterKnife
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        // 解决华为手机内存泄漏
        FixMemLeak.fixLeak(this);
        // 隐藏LoadingDialog
        hideLoadingDialog();
        // 从Activity堆栈移除
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        if (isAactivityAnimation()) {
            overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
        }
    }

    /**
     * 清单文件里设置了Activity的configChanges属性后会回调该方法
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 应用从后台切到前台回调方法
     */
    @Override
    public void onFront() {
    }

    /**
     * 应用从前台切到后台回调方法
     */
    @Override
    public void onBackground() {

    }

    /**
     * 获取布局ID
     *
     * @return LayoutResID
     */
    protected abstract int getLayoutResID();

    @Override
    public void initView() {
        // 设置状态栏占位View的高度
        setStatusBarHeight(R.id.view_status_bar);
        // 设置actionbar back点击退出事件
        setBack(R.id.iv_action_bar_back);
    }

    /**
     * 设置状态栏占位View的高度
     */
    @Override
    public void setStatusBarHeight(int statusBarResId) {
        if (isTranslucentStatusBar()) {
            View statusBarView = findViewById(statusBarResId);
            if (statusBarView != null) {
                int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
                ViewUtils.setHeight(statusBarView, statusBarHeight);
            }
        }
    }

    /**
     * 设置actionbar back点击退出事件
     */
    @Override
    public void setBack(int backResId) {
        View backView = findViewById(backResId);
        if (backView != null) {
            backView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        // 检测版本更新
        detectVersionUpdate();
    }

    @Override
    public void setActionBarTitle(int titleStringResId) {
        setActionBarTitle(getString(titleStringResId));
    }

    @Override
    public void setActionBarTitle(String titleString) {
        setActionBarTitle(R.id.tv_action_bar_title, titleString);
    }

    /**
     * 设置ActionBar标题
     */
    @Override
    public void setActionBarTitle(int titleViewResId, int titleStringResId) {
        setActionBarTitle(titleViewResId, getString(titleStringResId));
    }

    /**
     * 设置ActionBar标题
     */
    @Override
    public void setActionBarTitle(int titleViewResId, String titleString) {
        if (findViewById(titleViewResId) != null && (findViewById(titleViewResId) instanceof TextView)) {
            ((TextView) findViewById(titleViewResId)).setText(titleString);
        }
    }

    /**
     * 显示Loading
     */
    @Override
    public void showLoadingDialog() {
        showLoadingDialog(null);
    }

    /**
     * 显示带有文本的LoadingDialog
     */
    @Override
    public void showLoadingDialog(CharSequence text) {
        if (!isDestroyed()) {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog.Builder(this)
                        .build();
            }
            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.setMessage(text);
                mLoadingDialog.show();
            }
        }
    }

    /**
     * 隐藏Loading
     */
    @Override
    public void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        ToastUtil.showToast(this, resId);
    }

    @Override
    public void showToast(String text) {
        ToastUtil.showToast(this, text);
    }

    /**
     * 是否透明状态栏，默认不使用透明状态栏
     *
     * @return
     */
    @Override
    public boolean isTranslucentStatusBar() {
        return false;
    }

    /**
     * 设置透明状态栏
     */
    @Override
    public void translucentStatusBar() {
        if (isTranslucentStatusBar()) {
            // 设置透明状态栏
            StatusBarUtils.translucentStatusBar(this);
        }
    }

    /**
     * 是否状态栏亮色模式，默认深色模式
     */
    @Override
    public boolean isStatusBarLightMode() {
        return false;
    }

    /**
     * 设置状态栏亮色模式
     */
    @Override
    public void statusBarLightMode() {
        if (isStatusBarLightMode()) {
            StatusBarUtils.setMStatusBarLightMode(this);
        }
    }

    /**
     * 设置状态栏颜色
     */
    @Override
    public void setStatusBarColor(int color) {
        StatusBarUtils.setMStatusBarColor(this, color);
    }

    /***
     * 是否使用Activity转场动画，默认使用
     */
    @Override
    public boolean isAactivityAnimation() {
        return false;
    }

    @Override
    public void startActivity(@NonNull Class clazz) {
        ActivityUtils.startActivity(this, clazz);
    }

    @Override
    public void startActivity(@NonNull Class clazz, Bundle bundle) {
        ActivityUtils.startActivity(this, clazz, bundle);
    }

    @Override
    public void startActivityForResult(@NonNull Class clazz, int requestCode) {
        ActivityUtils.startActivityForResult(this, clazz, requestCode);
    }

    @Override
    public void startActivityForResult(@NonNull Class clazz, Bundle bundle, int requestCode) {
        ActivityUtils.startActivityForResult(this, clazz, bundle, requestCode);
    }

    @Override
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    /**
     * 显示软键盘
     */
    @Override
    public void showSoftInput(View view) {
        InputMethodUtils.showSoftInput(view);
    }

    /**
     * 隐藏软键盘
     */
    @Override
    public void hideSoftInput(View view) {
        InputMethodUtils.hideSoftInput(view);
    }

    /**
     * 显示PopupWindow
     */
    @Override
    public void showPw(PopupWindow pw, View anchor, int gravity) {
        if (pw != null && !pw.isShowing()) {
            pw.showAsDropDown(anchor, 0, 0, gravity);
        }
    }

    @Override
    public void showPw(PopupWindow pw, View anchor, int gravity, int xoff, int yoff) {
        if (pw != null && !pw.isShowing()) {
            pw.showAsDropDown(anchor, xoff, yoff, gravity);
        }
    }

    /**
     * 隐藏PopupWindow
     */
    @Override
    public void dismissPw(PopupWindow pw) {
        if (pw != null && pw.isShowing()) {
            pw.dismiss();
        }
    }

    /**
     * 是否检测版本更新（Bugly应用内版本更新、UpdatePlugin应用内版本更新）；
     * 当前只在MainActivity检测版本更新；
     */
    @Override
    public boolean isDetectVersionUpdate() {
        return false;
    }

    /**
     * 检测版本更新，注意后台版本更新接口配置的version_code要与
     * 服务器上传apk的version_code一致，不然会出现apk下载成功但
     * 检测到版本号不一致而提示下载失败的情况
     */
    private void detectVersionUpdate() {
        if (isDetectVersionUpdate()) {
            // 启动更新任务
            UpdateBuilder.create()
                    // .setCheckCallback(new CheckCallbackImpl())
                    // .setDownloadCallback(new DownloadCallbackImpl())
                    .setCheckNotifier(new DefaultUpdateNotifier())
                    .setDownloadNotifier(new DefaultDownloadNotifier())
                    .setInstallNotifier(new DefaultInstallNotifier())
                    .setFileCreator(new DefaultFileCreator())
                    .setUpdateStrategy(new CustomUpdateStrategy())
                    .check();
        }
    }

    @Override
    public boolean isShowFloating() {
        return false;
    }
}