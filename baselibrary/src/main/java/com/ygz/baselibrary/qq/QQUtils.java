package com.ygz.baselibrary.qq;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.ygz.baselibrary.R;
import com.ygz.baselibrary.util.ContextUtil;
import com.ygz.baselibrary.util.PackageUtils;
import com.ygz.baselibrary.util.StringUtils;
import com.ygz.baselibrary.util.ToastUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * @Date: 2019/10/11 10:41
 * @Author: YGZ
 * @Description: QQ opensdk相关工具类
 * @Version:
 */
public class QQUtils {

    private Context mContext;
    // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
    private Tencent mTencent;

    /**
     * 获取QQUtils实例
     */
    public static QQUtils getInstance(String appId) {
        return new QQUtils(appId);
    }

    /**
     * 私有构造方法
     */
    private QQUtils(String appId) {
        this.mContext = ContextUtil.getContext();
        /**
         * 创建一个Tencent实例
         * 参数1：其中APP_ID是分配给第三方应用的appid，类型为String；
         * 参数2：1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取；
         */
        mTencent = Tencent.createInstance(appId, mContext);
    }

    /**
     * 获取Tencent实例
     */
    public Tencent getTencent() {
        return mTencent;
    }

    /**
     * 判断设备是否安装QQ
     */
    public boolean qqNotInstalled() {
        if (PackageUtils.isPackageInstalledByGetInfo(mContext, "com.tencent.mobileqq")) {
            return false;
        } else {
            ToastUtil.showToast(mContext, R.string.qq_not_installed);
            return true;
        }
    }

    /**
     * 调用QQ登录接口：
     * 如果用户手机上安装了最新版本的手机QQ，将会调用相应的客户端，通过SSO方式进行登录。
     * 此方式可以避免用户多次输入用户名和密码，提升用户的登录体验。
     * 授权完成后，界面会自动返回应用界面。用户在应用中进行后续操作。
     */
    private void login(BaseUiListener listener) {
        if (qqNotInstalled()) {
            return;
        }

        if (!mTencent.isSessionValid()) {
            // mTencent.login(this, Scope, listener);
        }
    }

    /**
     * 调用QQ注销接口
     */
    private void logout() {
        if (qqNotInstalled()){
            return;
        }

        mTencent.logout(mContext);
    }

    /**
     * 分享图文消息到QQ
     *
     * @param activity  Activity
     * @param targetUrl 点击后的跳转url
     * @param title     标题，最长30个字符
     * @param summary   消息摘要，最长40个字符
     * @param imageUrl  图片的url或者本地路径
     * @param appName   手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     */
    public void shareWebpageToQQ(Activity activity, String targetUrl, String title, String summary, String imageUrl, String appName) {
        if (qqNotInstalled()){
            return;
        }

        final Bundle params = new Bundle();
        // 分享的类型，图文分享（普通分享）填Tencent.SHARE_TO_QQ_TYPE_DEFAULT
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, targetUrl);
        String shareTitle = StringUtils.substringByLength(title, 30);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
        String shareSummary = StringUtils.substringByLength(summary, 40);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareSummary);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, imageUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        /**
         * 分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
         * Tencent.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
         * Tencent.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮。
         */
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        shareToQQ(activity, params, new BaseUiListener());
    }

    /**
     * 分享纯图片到QQ
     *
     * @param activity      Activity
     * @param imageLocalUrl 本地图片路径
     * @param appName       手Q客户端顶部，替换“返回”按钮文字，如果为空，用返回代替
     */
    public void shareImageToQQ(Activity activity, String imageLocalUrl, String appName) {
        if (qqNotInstalled()){
            return;
        }

        final Bundle params = new Bundle();
        // 分享的类型，纯图片分享填Tencent.SHARE_TO_QQ_TYPE_IMAGE
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, imageLocalUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, appName);
        /**
         * 分享额外选项，两种类型可选（默认是不隐藏分享到QZone按钮且不自动打开分享到QZone的对话框）：
         * Tencent.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN，分享时自动打开分享到QZone的对话框。
         * Tencent.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE，分享时隐藏分享到QZone按钮。
         */
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        shareToQQ(activity, params, new BaseUiListener());
    }

    /**
     * 分享消息到QQ（无需QQ登录）
     *
     * @param activity 调用者所在Activity
     * @param params   KV参数对，具体取值见下表
     * @param listener 回调
     */
    private void shareToQQ(Activity activity, Bundle params, IUiListener listener) {
        mTencent.shareToQQ(activity, params, listener);
    }

}
