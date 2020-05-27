package com.egee.baselibrary.update;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import org.lzh.framework.updatepluginlib.base.InstallStrategy;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.util.UpdateInstallProvider;

import java.io.File;

/**
 * @Date: 2019/10/19 18:22
 * @Author: YGZ
 * @Description: 定制安装策略，适配Android7.0安装方案。
 * @Version:
 */
public class DefaultInstallStrategy extends InstallStrategy {

    private static String DEFAULT_AUTHOR = null;

    @Override
    public void install(Context context, String filename, final Update update) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        File apkFile = new File(filename);
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            // Adaptive with api version 24+
            uri = UpdateInstallProvider.getUriByFile(apkFile, getAuthor(context));
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    private String getAuthor(Context context) {
        if (TextUtils.isEmpty(DEFAULT_AUTHOR)) {
            DEFAULT_AUTHOR = "update.plugin." + context.getPackageName() + ".UpdateInstallProvider";
        }
        return DEFAULT_AUTHOR;
    }

}