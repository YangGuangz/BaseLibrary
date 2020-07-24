package com.ygz.baselibrary.anim;

import android.animation.Animator;

/**
 * @Date: 2019/11/6 21:56
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class AnimatorListenerWrap implements Animator.AnimatorListener {

    @Override
    public void onAnimationStart(Animator animator) {
        onAnimationStartWrap(animator);
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        onAnimationEndWrap(animator);
    }

    @Override
    public void onAnimationCancel(Animator animator) {
        onAnimationCancelWrap(animator);
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
        onAnimationRepeatWrap(animator);
    }

    public void onAnimationStartWrap(Animator animator) {

    }

    public void onAnimationEndWrap(Animator animator) {

    }

    public void onAnimationCancelWrap(Animator animator) {

    }

    public void onAnimationRepeatWrap(Animator animator) {

    }

}
