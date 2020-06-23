package com.ygz.baselibrary.base;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;

/**
 * @Date: 2020/4/11 10:43
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class BaseDialogFragment extends DialogFragment {

    public Activity mActivity;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void show(@NotNull FragmentManager manager, String tag) {
        /*try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag).addToBackStack(null);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
        }*/

        if (getDialog() != null && getDialog().isShowing()) {
            return;
        }
        // 解决 throw new IllegalStateException("Fragment already added: " + fragment)
        if (!isDetached() && manager.getFragments().contains(this)) {
            return;
        }
        super.show(manager, tag);
    }

    protected void showLoadingDialog() {
        if (mActivity != null && mActivity instanceof BaseActivity) {
            ((BaseActivity) mActivity).showLoadingDialog();
        }
    }

    protected void hideLoadingDialog() {
        if (mActivity != null && mActivity instanceof BaseActivity) {
            ((BaseActivity) mActivity).hideLoadingDialog();
        }
    }

}