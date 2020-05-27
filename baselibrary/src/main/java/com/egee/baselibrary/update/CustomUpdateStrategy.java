package com.egee.baselibrary.update;

import org.lzh.framework.updatepluginlib.base.UpdateStrategy;
import org.lzh.framework.updatepluginlib.model.Update;

/**
 * @Date: 2019/10/19 17:47
 * @Author: YGZ
 * @Description: 定制更新时各节点通知的显示逻辑
 * @Version:
 */
public class CustomUpdateStrategy extends UpdateStrategy {

    @Override
    public boolean isShowUpdateDialog(Update update) {
        // 在检查到有更新时，是否显示弹窗通知。
        return true;
    }

    @Override
    public boolean isAutoInstall() {
        // 在下载完成后。是否自动进行安装
        return false;
    }

    @Override
    public boolean isShowDownloadDialog() {
        // 在下载过程中，是否显示下载进度通知
        return true;
    }

}