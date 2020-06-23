package com.ygz.baselibrary.net.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Date: 2019/10/18 11:07
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> getTransformer() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
