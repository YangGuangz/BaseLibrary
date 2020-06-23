package com.ygz.baselibrary.widget.statusbar;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @Date: 2019/7/28 20:12
 * @Author: YGZ
 * @Description: If you use translucentStatusBar and set windowSoftInputMode="adjustResize" in Activity with EditText,it will be not work together;
 * Than you can set fitsSystemWindows="true" in the root layout,but you will find that the translucentstatusbar is not work;
 * So you should override root layout and override method fitsystemwindow to make sure that translucentstatusbar and adjustresize work together;
 * @Version:
 */
public class WrapLinearLayout extends LinearLayout {

    private int[] mInsets = new int[4];

    public WrapLinearLayout(Context context) {
        super(context);
    }

    public WrapLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final int[] getInsets() {
        return mInsets;
    }

    @Override
    protected final boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Intentionally do not modify the bottom inset. For some reason,
            // if the bottom inset is modified, window resizing stops working.
            mInsets[0] = insets.left;
            mInsets[1] = insets.top;
            mInsets[2] = insets.right;

            insets.left = 0;
            insets.top = 0;
            insets.right = 0;
        }
        return super.fitSystemWindows(insets);
    }

}