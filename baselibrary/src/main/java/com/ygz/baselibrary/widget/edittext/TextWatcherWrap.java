package com.ygz.baselibrary.widget.edittext;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @Date: 2019/7/27 15:54
 * @Author: YGZ
 * @Description: TextWatcher包装类
 * @Version:
 */
public class TextWatcherWrap implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        beforeTextChangedWrap(charSequence, i, i1, i2);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        onTextChangedWrap(charSequence, i, i1, i2);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        afterTextChangedWrap(editable);
    }

    public void beforeTextChangedWrap(CharSequence charSequence, int i, int i1, int i2) {

    }

    public void onTextChangedWrap(CharSequence charSequence, int i, int i1, int i2) {

    }

    public void afterTextChangedWrap(Editable editable) {

    }

}