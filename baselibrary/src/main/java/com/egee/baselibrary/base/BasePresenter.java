package com.egee.baselibrary.base;

import androidx.annotation.NonNull;

import com.egee.baselibrary.net.rx.RxManager;

/**
 * @Date: 2019/10/16 20:04
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public abstract class BasePresenter<M, V> {

    public M mModel;
    public V mView;
    protected RxManager mRxManager = new RxManager();

    /**
     * 返回presenter持有的Model引用
     *
     * @return
     */
    /*public abstract M getModel();*/

    /**
     * 绑定Model和View的引用
     *
     * @param m model
     * @param v view
     */
    public void attachMV(@NonNull M m, @NonNull V v) {
        this.mModel = m;
        this.mView = v;
    }

    /**
     * 解绑Model和View
     */
    public void detachMV() {
        mRxManager.dispose();
        mModel = null;
        mView = null;
    }

}