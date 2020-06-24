package com.ygz.baselibrary.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @Date: 2019/10/11 13:30
 * @Author: YGZ
 * @Description: View工具类
 * @Version:
 */
public class ViewUtils {

    /**
     * 设置控件是否Visible
     *
     * @param view      控件
     * @param isVisible 是否Visible
     */
    public static void setIsVisible(View view, boolean isVisible) {
        if (view != null)
            view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置控件是否Gone
     *
     * @param view      控件
     * @param isVisible 是否Visible
     */
    public static void setIsGone(View view, boolean isVisible) {
        if (view != null)
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置view的宽
     *
     * @param view  设置宽控件的
     * @param width 设置的宽度
     * @return
     */
    public static void setWidth(View view, int width) {
        if (view != null) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.width = width;
            view.setLayoutParams(lp);
        }
    }

    /**
     * 设置view的高
     *
     * @param view   设置高的控件
     * @param height 设置的高度
     * @return
     */
    public static void setHeight(View view, int height) {
        if (view != null) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.height = height;
            view.setLayoutParams(lp);
        }
    }

    /**
     * 设置view的宽高
     *
     * @param view   设置宽高的控件
     * @param width  设置的宽度
     * @param height 设置的高度
     * @return
     */
    public static void setWidthAndHeight(View view, int width, int height) {
        if (view != null) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.width = width;
            lp.height = height;
            view.setLayoutParams(lp);
        }
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    private static void measureWidthAndHeight(View view) {
        if (view != null) {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(w, h);
        }
    }

    /**
     * 得到view的宽高
     *
     * @param activity Activity
     * @param view     IView
     * @return view宽高数组
     */
    public static int[] getWidthAndHeight(Activity activity, View view) {
        if (activity == null || view == null) {
            return new int[]{0, 0};
        }
        measureWidthAndHeight(view);
        int w = view.getMeasuredWidth();
        int h = view.getMeasuredHeight();
        return new int[]{w, h};
    }

    /**
     * 设置控件权重
     *
     * @param view   控件，该控件的父控件需要为LinearLayout
     * @param weight 权重
     */
    public static void setWeight(View view, float weight) {
        if (view != null && (view.getParent() instanceof LinearLayout)) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, weight);
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 获取根布局
     *
     * @param activity
     * @return
     */
    public static View getRootView(Activity activity) {
        View rootView = null;
        if (activity != null)
            rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        return rootView;
    }

    /**
     * 设置布局margin
     *
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public static void setMargin(View view, int left, int top, int right, int bottom) {
        if (view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.setMargins(left, top, right, bottom);
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置TextView的DrawableLeft
     *
     * @param tv   TextView
     * @param left DrawableLeft
     */
    public static void setDrawableLeft(TextView tv, int left) {
        if (tv != null && left != 0)
            tv.setCompoundDrawablesWithIntrinsicBounds(left, 0, 0, 0);
    }

    /**
     * 将View的内容保存为图像的方法：
     * 创建一个新的Bitmap，然后再根据它来创建一个Canvas，最后调用View的draw方法将View画到Canvas上，这样得到的Bitmap就是我们想要的。
     *
     * @param view
     * @return
     */
    public static Bitmap viewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        int width = view.getWidth();
        int height = view.getHeight();

        // 创建一个新的Bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // 如果不设置canvas画布为白色，则生成透明
        canvas.drawColor(Color.WHITE);

        view.layout(0, 0, width, height);
        view.draw(canvas);

        return bitmap;
    }

    /**
     * 手动测量摆放View：
     * 对于手动 inflate 或者其他方式代码生成加载的View进行测量，避免该View无尺寸
     *
     * @param v
     * @param width
     * @param height
     */
    public static void layoutView(View v, int width, int height) {
        // validate view.width and view.height
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        // validate view.measurewidth and view.measureheight
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }

    /**
     * 获取一个 View 的缓存视图，前提是这个View已经渲染完成显示在页面上
     *
     * @param view
     * @return
     */
    public static Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    public static Bitmap createBitmap(View view) {
        view.buildDrawingCache();
        return view.getDrawingCache();

    }

    /**
     * Scrollview截屏
     *
     * @param scrollView 要截图的ScrollView
     * @return Bitmap
     */
    public static Bitmap shotScrollView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

}
