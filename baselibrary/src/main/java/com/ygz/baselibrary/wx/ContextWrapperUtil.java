package com.ygz.baselibrary.wx;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * @Date: 2019/10/13 16:09
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class ContextWrapperUtil extends ContextWrapper {

    private String packageName;

    public ContextWrapperUtil(Context context, String packageName) {
        super(context);
        this.packageName = packageName;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }

}
