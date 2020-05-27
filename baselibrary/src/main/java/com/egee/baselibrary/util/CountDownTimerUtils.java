package com.egee.baselibrary.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * @Date: 2019/10/10 17:10
 * @Author: YGZ
 * @Description: 倒计时工具类
 * @Version:
 */
public class CountDownTimerUtils extends CountDownTimer {

    /**
     * TextView软引用
     */
    private WeakReference<TextView> mWr;

    /**
     * @param millisInFuture    The number of millis in the future from the call to {@link #start()} until the countdown is done and {@link #onFinish()} is called.
     * @param countDownInterval The interval along the way to receiver {@link #onTick(long)} callbacks.
     * @param textView          倒计时控件
     */
    public CountDownTimerUtils(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        mWr = new WeakReference(textView);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (mWr.get() != null) {
            TextView textView = mWr.get();
            // 设置不可点击
            textView.setClickable(false);
            textView.setEnabled(false);
        }
    }

    @Override
    public void onFinish() {
        if (mWr.get() != null) {
            TextView textView = mWr.get();
            // 重新获得点击
            textView.setClickable(true);
            textView.setEnabled(true);
        }
    }

}  