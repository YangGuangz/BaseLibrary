package com.ygz.baselibrary.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Date: 2019/10/16 15:03
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public interface IBaseFragment extends IBaseView {

    void initView(View view);

    boolean isLazyFragment();

    void initData(@Nullable Bundle savedInstanceState);

    void startActivity(@NonNull Class clazz);

    void startActivity(@NonNull Class clazz, Bundle bundle);

    void startActivityForResult(@NonNull Class clazz, int requestCode);

    void startActivityForResult(@NonNull Class clazz, Bundle bundle, int requestCode);

}