package com.egee.baselibrary.update;

import com.egee.baselibrary.util.ContextUtil;
import com.egee.baselibrary.util.ToastUtil;

import org.lzh.framework.updatepluginlib.base.DownloadCallback;

import java.io.File;

/**
 * @Date: 2019/10/19 17:16
 * @Author: YGZ
 * @Description: 执行文件下载时的回调通知实现类
 * @Version:
 */
public class DownloadCallbackImpl implements DownloadCallback {

    @Override
    public void onDownloadStart() {
        // 下载更新包开始
        ToastUtil.showToast(ContextUtil.getContext(), "开始下载新版本");
    }

    @Override
    public void onDownloadProgress(long current, long total) {
        // 下载更新包进度通知
    }

    @Override
    public void onDownloadComplete(File file) {
        // 下载更新包完成
        ToastUtil.showToast(ContextUtil.getContext(), "新版本下载完成");
    }

    @Override
    public void onDownloadError(Throwable t) {
        // 下载更新包出错
        ToastUtil.showToast(ContextUtil.getContext(), "新版本下载失败");
    }

}