package com.egee.baselibrary.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * @Date: 2019/10/10 10:46
 * @Author: YGZ
 * @Description: 吐司工具类
 * @Version:
 */
public class ToastUtil {

    private static Toast sToast;

    /**
     * 显示短时吐司
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void showToast(Context context, String text) {
        if (context == null || TextUtils.isEmpty(text)) {
            return;
        }
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
        sToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        sToast.setGravity(Gravity.CENTER, 0, 0);
        sToast.show();
    }

    /**
     * 显示短时吐司
     *
     * @param context 上下文
     * @param resId   文本资源id
     */
    public static void showToast(Context context, @StringRes int resId) {
        try {
            String text = context.getApplicationContext()
                    .getResources()
                    .getText(resId)
                    .toString();
            showToast(context, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 极光推送Toast方法
     *
     * @param toast
     * @param context
     */
    @Deprecated
    public static void showToast(final String toast, final Context context) {
        showToast(context, toast);
    }

}
