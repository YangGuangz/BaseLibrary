package com.ygz.baselibrary.base;

import android.os.Bundle;

import com.ygz.baselibrary.util.ClassReflectHelper;

/**
 * @Date: 2019/10/16 20:08
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public abstract class BaseMvpActivity<P extends BasePresenter, M extends IBaseModel> extends BaseActivity {

    /**
     * 具体的presenter由子类确定
     */
    protected P mPresenter;

    /**
     * 具体的model由子类确定
     */
    protected M mModel;

    @Override
    public void initData(Bundle savedInstanceState) {
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
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachMV();
        }
    }

}
