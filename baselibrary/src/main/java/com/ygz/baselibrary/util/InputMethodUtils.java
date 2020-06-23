package com.ygz.baselibrary.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @Date: 2019/10/10 11:16
 * @Author: YGZ
 * @Description: 软键盘工具类
 * @Version:
 */
public class InputMethodUtils {

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showSoftInput(View view) {
        try {
            if (view != null) {
                Context context = view.getContext();
                if (context != null) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        view.requestFocus();
                        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftInput(View view) {
        try {
            if (view != null) {
                Context context = view.getContext();
                if (context != null) {
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
