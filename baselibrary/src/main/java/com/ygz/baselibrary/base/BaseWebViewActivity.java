package com.ygz.baselibrary.base;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ygz.baselibrary.R;

/**
 * @Date: 2019/10/17 22:30
 * @Author: YGZ
 * @Description: WebView基类
 * @Version:
 */
public abstract class BaseWebViewActivity<P extends BasePresenter, M extends IBaseModel> extends BaseMvpActivity<P, M> {

    private WebView mWebView;

    /**
     * 获取WebView
     *
     * @return WebView
     */
    protected abstract WebView getWebView();

    /**
     * 返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        fixWebViewLeak();
        // 在WebView处理之后调用
        super.onDestroy();
    }

    /**
     * 防止Android 5.1上WebView引起的内存泄露
     */
    private void fixWebViewLeak() {
        try {
            if (mWebView == null) {
                return;
            }
            // 在销毁WebView之前需要先将WebView从父容器中移除，然后再销毁WebView
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        super.initView();
        setBack(R.id.iv_action_bar_back);
        initWebView();
    }

    private void initWebView() {
        mWebView = getWebView();
        if (mWebView == null) {
            return;
        }

        WebSettings settings = mWebView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        // 设置支持Javascript交互
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(true);
        // settings.setSupportZoom(true);
        settings.setTextZoom(100);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    @Override
    public void setBack(int backResId) {
        if (findViewById(backResId) != null) {
            findViewById(backResId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mWebView != null && mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {
                        finish();
                    }
                }
            });
        }
    }

}
