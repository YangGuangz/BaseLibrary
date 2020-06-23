package com.ygz.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ygz.baselibrary.util.ClassReflectHelper;

/**
 * @Date: 2019/10/16 16:15
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public abstract class BaseMvpFragment<P extends BasePresenter, M extends IBaseModel> extends BaseFragment {

    /**
     * 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * 具体的model由子类确定
     */
    protected M mModel;

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        // mPresenter = (P) initPresenter();
        mPresenter = ClassReflectHelper.getT(this, 0);
        mModel = ClassReflectHelper.getT(this, 1);
        if (mPresenter != null) {
            // mModel = (M) mPresenter.getModel();
            if (mModel != null) {
                mPresenter.attachMV(mModel, this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

}
