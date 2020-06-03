package com.egee.baselibrary.widget.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

/**
 * @Date: 2020/4/10 17:01
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class RotateYAnimation extends Animation {

    // X轴Y轴的中心坐标
    private int centerX, centerY;
    // 初始化Camera
    private Camera camera = new Camera();
    // 旋转角度
    private float toDegrees = 360;
    // 动画的执行时间
    private long durationMillis = 3000L;
    // 插值器
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    public RotateYAnimation() {
    }

    public RotateYAnimation(float toDegrees, long durationMillis, Interpolator interpolator) {
        this.toDegrees = toDegrees;
        this.durationMillis = durationMillis;
        this.interpolator = interpolator;
    }

    /**
     * 在initialize对变量进行初始化
     *
     * @param width
     * @param height
     * @param parentWidth
     * @param parentHeight
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 获取X Y中心点坐标
        centerX = width / 2;
        centerY = height / 2;
        // 动画的执行时间
        setDuration(durationMillis);
        setInterpolator(interpolator);
    }

    /**
     * 在applyTransformation通过矩阵修改动画
     * <p>
     * 这里是自定义动画的核心，动画执行的过程中一直在回调这个方法
     * <p>
     * 每次回调这个方法interpolatedTime都会改变
     *
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Matrix matrix = t.getMatrix();
        camera.save();
        // 中心是绕Y轴旋转  这里可以自行设置为X轴 Y轴 Z轴
        camera.rotateY(toDegrees * interpolatedTime);
        // 把摄像头加在变换矩阵上
        camera.getMatrix(matrix);
        // 设置翻转中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        camera.restore();
    }

}