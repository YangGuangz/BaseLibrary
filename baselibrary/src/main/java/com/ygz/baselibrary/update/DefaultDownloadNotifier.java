package com.ygz.baselibrary.update;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import org.lzh.framework.updatepluginlib.base.DownloadCallback;
import org.lzh.framework.updatepluginlib.base.DownloadNotifier;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.util.ActivityManager;
import org.lzh.framework.updatepluginlib.util.SafeDialogHandle;

import java.io.File;

/**
 * @Date: 2019/10/19 17:29
 * @Author: YGZ
 * @Description: 默认使用的下载进度通知创建器: 在此创建Dialog弹窗显示并根据下载回调通知进行进度条更新
 * @Version:
 */
public class DefaultDownloadNotifier extends DownloadNotifier {

    @Override
    public DownloadCallback create(Update update, Activity activity) {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        SafeDialogHandle.safeShowDialog(dialog);
        return new DownloadCallback() {
            @Override
            public void onDownloadStart() {
            }

            @Override
            public void onDownloadComplete(File file) {
                SafeDialogHandle.safeDismissDialog(dialog);
            }

            @Override
            public void onDownloadProgress(long current, long total) {
                int percent = (int) (current * 1.0f / total * 100);
                dialog.setProgress(percent);
            }

            @Override
            public void onDownloadError(Throwable t) {
                SafeDialogHandle.safeDismissDialog(dialog);
                createRestartDialog();
            }
        };
    }

    private void createRestartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityManager.get().topActivity())
                .setCancelable(!update.isForced())
                .setTitle("下载apk失败")
                .setMessage("是否重新下载？")
                .setNeutralButton(update.isForced() ? "退出" : "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (update.isForced()) {
                            // 点击取消时杀死app
                            ActivityManager.get().exit();
                        } else {
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartDownload();
                    }
                });

        builder.show();
    }

}