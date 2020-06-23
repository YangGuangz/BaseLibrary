package com.ygz.baselibrary.base;

import android.view.View;
import android.widget.PopupWindow;

/**
 * @Date: 2019/10/16 18:33
 * @Author: YGZ
 * @Description: View层接口-定义V层需要作出的动作的接口(界面更新相关)
 * @Version:
 */
public interface IBaseView {

    /**
     * 初始化presenter，此方法返回的presenter对象不可为空
     */
    /*@NonNull
    BasePresenter initPresenter();*/

    /**
     * 显示LoadingDialog
     */
    void showLoadingDialog();

    /**
     * 显示带有提示信息的LoadingDialog
     *
     * @param text
     */
    void showLoadingDialog(CharSequence text);

    /**
     * 隐藏加载框
     */
    void hideLoadingDialog();

    /**
     * 弹吐司
     *
     * @param text 文本
     */
    void showToast(String text);

    /**
     * 弹吐司
     *
     * @param resId 文本资源id
     */
    void showToast(int resId);

    /**
     * 弹出软键盘
     *
     * @param view
     */
    void showSoftInput(View view);

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    void hideSoftInput(View view);

    /**
     * show PopupWindow
     */
    void showPw(PopupWindow pw, View anchor, int gravity);

    /**
     * show PopupWindow
     */
    void showPw(PopupWindow pw, View anchor, int gravity, int xoff, int yoff);

    /**
     * dismiss PopupWindow
     */
    void dismissPw(PopupWindow pw);

}
