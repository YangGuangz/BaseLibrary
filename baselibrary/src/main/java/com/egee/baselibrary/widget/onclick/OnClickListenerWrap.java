package com.egee.baselibrary.widget.onclick;

import android.view.View;

import com.egee.baselibrary.util.LogUtils;

/**
 * @Date: 2019/8/14 20:42
 * @Author: YGZ
 * @Description: 点击事件包装类，防止连续多次点击
 * @Version:
 */
public abstract class OnClickListenerWrap implements View.OnClickListener {

    /**
     * 上次点击系统时间毫秒值
     */
    private long mLastClickTime;
    /**
     * 上次点击与当前点击时间间隔，秒
     */
    private long mTimeInterval = 2000L;

    public OnClickListenerWrap() {
    }

    public OnClickListenerWrap(long interval) {
        this.mTimeInterval = interval;
    }

    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if (nowTime - mLastClickTime > mTimeInterval) {
            // 单次点击事件
            onSingleClick();
            mLastClickTime = nowTime;
        } else {
            // 快速点击事件
            onFastClick();
        }
    }

    protected abstract void onSingleClick();

    protected void onFastClick() {
        LogUtils.d("点击过快");
    }

}