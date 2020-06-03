package com.egee.baselibrary.qq;

import com.egee.baselibrary.util.LogUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * @Date: 2019/10/11 10:51
 * @Author: YGZ
 * @Description: 实现回调IUiListener，
 * 调用SDK已经封装好的接口时，例如：登录、快速支付登录、应用分享、应用邀请等接口，需传入该回调的实例。
 * 特别注意：
 * 应用调用Andriod_SDK接口时，如果要成功接收到回调，需要在调用接口的Activity的onActivityResult方法中增加如下代码：
 * @<code>
 * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 * mTencent.onActivityResult(requestCode, resultCode, data);
 * }
 * </code>
 * @Version:
 */
public class BaseUiListener implements IUiListener {

    @Override
    public void onComplete(Object o) {
        LogUtils.d("分享到qq完成");
    }

    @Override
    public void onCancel() {
        LogUtils.d("分享到qq取消");
    }

    @Override
    public void onError(UiError uiError) {
        LogUtils.d("分享到qq出错");
    }

}