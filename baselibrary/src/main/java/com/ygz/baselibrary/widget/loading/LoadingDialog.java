package com.ygz.baselibrary.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ygz.baselibrary.R;

import java.lang.ref.WeakReference;

/**
 * @Description:
 * @CreateBy: YGZ
 * @CreateDate: 2020/7/18
 * @UpdateBy:
 * @UpdateDate:
 * @UpdateNotes:
 * @Version: 1.0.0
 */
public class LoadingDialog extends Dialog {

    private ProgressBar mProgressBar;
    private TextView mTvMessage;

    private WeakReference<Context> mContext = new WeakReference<>(null);

    public LoadingDialog(Context context) {
        super(context, R.style.CustomProgressDialog);

        mContext = new WeakReference<>(context);

        // 这里不能使用getLayoutInflater()填充布局，ProgressBar样式会有问题
        // View view = getLayoutInflater().inflate(R.layout.dialog_loading, null);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        mProgressBar = view.findViewById(R.id.pb_loading);
        mTvMessage = view.findViewById(R.id.tv_loading);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view, params);

        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 设置加载框ProgressBar颜色
     *
     * @param text msg
     */
    public void setIndeterminateTint(CharSequence text) {
        if (mProgressBar != null) {
            /*ColorStateList tint = new ColorStateList();
            mProgressBar.setIndeterminateTintList(tint);*/
        }
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
                mTvMessage.setText(text);
                mTvMessage.setVisibility(View.VISIBLE);
            }
        }
    }

}