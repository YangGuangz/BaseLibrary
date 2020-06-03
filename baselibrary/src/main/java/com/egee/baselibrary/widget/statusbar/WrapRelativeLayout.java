package com.egee.baselibrary.widget.statusbar;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @Date: 2019/7/28 20:13
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class WrapRelativeLayout extends RelativeLayout {

    private int[] mInsets = new int[4];

    public WrapRelativeLayout(Context context) {
        super(context);
    }

    public WrapRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
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