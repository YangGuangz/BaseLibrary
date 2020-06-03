package com.egee.baselibrary.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.egee.baselibrary.R;

/**
 * @Date: 2019/10/19 16:36
 * @Author: YGZ
 * @Description: 自定义的LoadingDialog
 * @Version:
 */
public class LoadingDialog extends ProgressDialog {

    private View mDialogView;
    private TextView mTvMessage;
    private boolean canceledOnTouchOutside;

    public LoadingDialog(LoadingDialog.Builder builder) {
        super(builder.mContext);
        mDialogView = builder.mDialogView;
        mTvMessage = builder.mTvMessage;
        canceledOnTouchOutside = builder.canceledOnTouchOutside;
    }

    private LoadingDialog(LoadingDialog.Builder builder, int themeResId) {
        super(builder.mContext, themeResId);
        mDialogView = builder.mDialogView;
        mTvMessage = builder.mTvMessage;
        canceledOnTouchOutside = builder.canceledOnTouchOutside;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mDialogView);
        setCancelable(true);
        setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 设置加载框提示信息
     *
     * @param text msg
     */
    public void setMessage(CharSequence text) {
        if (mTvMessage != null) {
            if (TextUtils.isEmpty(text)) {
                mTvMessage.setVisibility(View.GONE);
            } else {
                mTvMessage.setVisibility(View.VISIBLE);
                mTvMessage.setText(text);
            }
        }
    }

    /**
     * Builder
     */
    public static final class Builder {

        private Context mContext;
        private View mDialogView;
        private TextView mTvMessage;
        private int mTheme = -1;
        private boolean canceledOnTouchOutside;

        public Builder(Context context) {
            this.mContext = context;
            mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
            mTvMessage = mDialogView.findViewById(R.id.tv_loading);
        }

        /**
         * 设置加载框样式
         *
         * @param theme 样式
         * @return this
         */
        public LoadingDialog.Builder setTheme(int theme) {
            this.mTheme = theme;
            return this;
        }

        /**
         * 设置点击加载框以外区域是否取消加载框
         *
         * @param cancel canceledOnTouchOutside
         * @return this
         */
        public LoadingDialog.Builder setCanceledOnTouchOutside(boolean cancel) {
            canceledOnTouchOutside = cancel;
            return this;
        }

        public LoadingDialog build() {
            if (mTheme != -1) {
                return new LoadingDialog(this, mTheme);
            } else {
                return new LoadingDialog(this);
            }
        }

    }

}