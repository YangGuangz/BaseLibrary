package com.ygz.test;

import android.view.View;

import com.ygz.baselibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        findViewById(R.id.tv_show_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((int) (Math.random() * 100)%2 ) == 0) {

                    showLoadingDialog();
                }else {
                    showLoadingDialog("loading...");
                }
            }
        });
    }
}
