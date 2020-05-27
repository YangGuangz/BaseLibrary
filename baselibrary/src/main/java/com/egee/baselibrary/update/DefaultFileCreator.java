package com.egee.baselibrary.update;

import android.content.Context;

import org.lzh.framework.updatepluginlib.base.FileCreator;
import org.lzh.framework.updatepluginlib.model.Update;
import org.lzh.framework.updatepluginlib.util.ActivityManager;

import java.io.File;

/**
 * @Date: 2019/10/19 18:27
 * @Author: YGZ
 * @Description: 默认使用的APK下载文件创建器。
 * @Version:
 */
public class DefaultFileCreator extends FileCreator {

    @Override
    public File create(Update update) {
        File cacheDir = getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir, "update_normal_" + update.getVersionName());
    }

    @Override
    public File createForDaemon(Update update) {
        File cacheDir = getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir, "update_daemon_" + update.getVersionName());
    }

    /**
     * 获取应用缓存路径：/storage/emulated/0/Android/data/com.egee.ddzx/cache/update/xxx，
     * 不需要申请动态权限。
     *
     * @return
     */
    private File getCacheDir() {
        Context context = ActivityManager.get().getApplicationContext();
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        cacheDir = new File(cacheDir, "update");
        return cacheDir;
    }

}