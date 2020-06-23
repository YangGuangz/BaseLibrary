package com.ygz.baselibrary.net.rx;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Date: 2019/10/18 19:50
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class RxManager {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    /**
     * 添加observer
     *
     * @param disposable
     */
    public void add(Disposable disposable) {
        if (disposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    public void dispose() {
        // 取消订阅
        mCompositeDisposable.dispose();
    }

}
