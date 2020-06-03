package com.egee.baselibrary.widget.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.egee.baselibrary.util.SizeUtils;

/**
 * @Date: 2019/8/7 13:47
 * @Author: YGZ
 * @Description: 自定义的封装PopupWindow
 * @Version:
 */
public class CustomPopupWindow extends PopupWindow {

    private View contentView;

    public CustomPopupWindow(Builder builder) {
        super(builder.contentView, builder.width, builder.height, builder.focusable);
        this.contentView = builder.contentView;
        setOutsideTouchable(builder.outsideTouchable);
        setBackgroundDrawable(new ColorDrawable(0));
    }

    public View findViewById(int id) {
        return contentView.findViewById(id);
    }

    public static final class Builder {

        private Context context;
        private View contentView;
        private int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        private int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        private boolean focusable = true;
        private boolean outsideTouchable = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder contentView(int resourse) {
            this.contentView = LayoutInflater.from(context).inflate(resourse, null);
            return this;
        }

        public Builder focusable(boolean focusable) {
            this.focusable = focusable;
            return this;
        }

        public Builder outsideTouchable(boolean touchable) {
            this.outsideTouchable = touchable;
            return this;
        }

        public Builder height(int dpVal) {
            if (dpVal > 0)
                this.height = SizeUtils.dp2px(dpVal);
            return this;
        }

        public Builder width(int dpVal) {
            if (dpVal > 0)
                this.width = SizeUtils.dp2px(dpVal);
            return this;
        }

        public Builder addOnClickListener(int id, View.OnClickListener listener) {
            if (contentView != null && contentView.findViewById(id) != null) {
                contentView.findViewById(id).setOnClickListener(listener);
            }
            return this;
        }

        public CustomPopupWindow build() {
            return new CustomPopupWindow(this);
        }

    }

}