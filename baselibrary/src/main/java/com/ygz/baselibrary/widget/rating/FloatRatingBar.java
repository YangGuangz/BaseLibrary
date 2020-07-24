package com.ygz.baselibrary.widget.rating;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ygz.baselibrary.R;

/**
 * @Date: 2020/7/16 15:53
 * @Author: YGZ
 * @Description: A RatingBar can set float rating(0-5)，need a selected star and unselected star。
 * @Version:
 */
public class FloatRatingBar extends LinearLayout {

    // 星星宽度
    private int starWidth;
    // 星星高度
    private int startHeight;
    // 星星之间的距离
    private int starSpacing;
    // 选中时的图片资源
    private int starForegroundRes;
    // 未选中时的图片资源
    private int starBackgroundRes;
    // 评分值
    private float starRating;


    public FloatRatingBar(Context context) {
        super(context);
        init();
    }

    public FloatRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FloatRatingBar);
        try {
            starWidth = (int) ta.getDimension(R.styleable.FloatRatingBar_floatRatingBarStarWidth, 50);
            startHeight = (int) ta.getDimension(R.styleable.FloatRatingBar_floatRatingBarStarHeight, 50);
            starForegroundRes = ta.getResourceId(R.styleable.FloatRatingBar_floatRatingBarStarForegroundDrawable, 0);
            starBackgroundRes = ta.getResourceId(R.styleable.FloatRatingBar_floatRatingBarStarBackgroundDrawable, 0);
            starSpacing = (int) ta.getDimension(R.styleable.FloatRatingBar_floatRatingBarStarSpacing, 10);
            starRating = ta.getFloat(R.styleable.FloatRatingBar_floatRatingBarRating, 0f);
        }finally {
            ta.recycle();
        }
        init();
        setRating(starRating);
    }

    private void init() {
        this.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < 5; i++) {
            RelativeLayout child = new RelativeLayout(getContext());
            ImageView ivBackground = new ImageView(getContext());
            ivBackground.setImageResource(starBackgroundRes);
            RelativeLayout.LayoutParams backLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            child.addView(ivBackground, backLayoutParams);

            ImageView ivForeground = new ImageView(getContext());
            ivForeground.setImageResource(starForegroundRes);
            RelativeLayout.LayoutParams foreLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            child.addView(ivForeground, foreLayoutParams);

            LayoutParams childLayoutParams = new LayoutParams(starWidth, startHeight);
            if (i != 0) {
                childLayoutParams.leftMargin = starSpacing;
            }
            Drawable drawable = ivForeground.getDrawable();
            drawable.setLevel(0);
            this.addView(child, childLayoutParams);
        }
    }

    /**
     * set rating
     * rating range in [0,5] including float number, such as 4.3
     *
     * @param rating
     */
    public void setRating(float rating) {
        if (rating < 0 || rating > 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            RelativeLayout child = (RelativeLayout) this.getChildAt(i);
            ImageView foreground = (ImageView) child.getChildAt(1);
            Drawable drawable = foreground.getDrawable();
            if (rating > i + 1) {
                drawable.setLevel(10000);
            } else {
                float left = rating - i;
                drawable.setLevel((int) (left * 10000));
                break;
            }
        }
    }
}
