package com.ygz.baselibrary.base;

/**
 * @Date: 2019/10/16 16:00
 * @Author: YGZ
 * @Description: 列表类型或者多状态切换页面View层接口-定义V层需要作出的动作的接口(界面更新相关)
 * @Version:
 */
public interface IBaseMutilStatusMvpView {

    /**
     * 显示加载中页面
     */
    void showLoading();

    /**
     * 隐藏加载中页面
     */
    void hideLoading();

    /**
     * 显示空数据页面
     */
    void showEmpty();

    /**
     * 隐藏空数据页面
     */
    void hideEmpty();

    /**
     * 显示错误页面
     */
    void showError();

    /**
     * 隐藏错误页面
     */
    void hideError();

    /**
     * 加载失败，点击重试
     */
    void retry();

}
