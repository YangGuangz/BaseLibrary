package com.egee.baselibrary.widget.animation;

import android.view.animation.Animation;

/**
 * @Date: 2019/10/23 14:00
 * @Author: YGZ
 * @Description: 动画监听包装类
 * @Version:
 */
public abstract class AnimationListenerWrap implements Animation.AnimationListener {

    @Override
    public void onAnimationStart(Animation animation) {
        onAnimationStartWrap(animation);
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        onAnimationEndWrap(animation);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        onAnimationRepeatWrap(animation);
    }

    private void onAnimationStartWrap(Animation animation) {

    }

    private void onAnimationEndWrap(Animation animation) {

    }

    private void onAnimationRepeatWrap(Animation animation) {

    }

}