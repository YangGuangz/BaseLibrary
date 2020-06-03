package com.egee.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.umeng.analytics.MobclickAgent;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Date: 2019/10/16 14:55
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public abstract class BaseFragment extends SupportFragment implements IBaseFragment {

    protected Activity mActivity;
    protected String TAG;
    protected View mFragmentView;
    private Unbinder mUnbinder;
    // 分页加载当前页数，默认第一页
    protected int mPage = 1;
    // 分页加载每页条数，默认每页15条
    protected int mPerPage = 15;

    /**
     * 在Fragment与Activity窗口关联后回调
     */
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 在Bundle对象中可以获取在Activity中传过来的数据
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建Fragment显示的View
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutResID() != 0) {
            return inflater.inflate(getLayoutResID(), null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     * @param view               onCreateView中返回的view
     * @param savedInstanceState 获取Fragment保存的转态，fragment没有onRestoreInstanceState方法
     */
    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Umeng页面统计
        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        // Umeng页面统计
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * 在onCreateView()中创建的视图将被移除
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        hideLoadingDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Fragment与Activity不再有关联
     */
    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected abstract int getLayoutResID();

    private void init(@NotNull View view, @Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        mFragmentView = view;
        mUnbinder = ButterKnife.bind(this, view);
        initView(view);
        if (!isLazyFragment()) {
            initData(savedInstanceState);
        }
    }

    /**
     * 可以通过重写该方法实现是否使用懒加载模式
     *
     * @return
     */
    @Override
    public boolean isLazyFragment() {
        return false;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        if (isLazyFragment()) {
            initData(savedInstanceState);
        }
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(null);
    }

    @Override
    public void showLoadingDialog(CharSequence text) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            // 该Fragment对象已经被添加到了它的Activity中
            ((BaseActivity) getActivity()).showLoadingDialog(text);
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).hideLoadingDialog();
        }
    }

    @Override
    public void showToast(String text) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).showToast(text);
        }
    }

    @Override
    public void showToast(int resId) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).showToast(resId);
        }
    }

    @Override
    public void showSoftInput(View view) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).showSoftInput(view);
        }
    }

    @Override
    public void hideSoftInput(View view) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).hideSoftInput(view);
        }
    }

    @Override
    public void showPw(PopupWindow pw, View anchor, int gravity) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).showPw(pw, anchor, gravity);
        }
    }

    @Override
    public void showPw(PopupWindow pw, View anchor, int gravity, int xoff, int yoff) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).showPw(pw, anchor, gravity, xoff, yoff);
        }
    }

    @Override
    public void dismissPw(PopupWindow pw) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).dismissPw(pw);
        }
    }

    @Override
    public void startActivity(@NonNull Class clazz) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).startActivity(clazz);
        }
    }

    @Override
    public void startActivity(@NonNull Class clazz, Bundle bundle) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).startActivity(clazz, bundle);
        }
    }

    @Override
    public void startActivityForResult(@NonNull Class clazz, int requestCode) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).startActivityForResult(clazz, requestCode);
        }
    }

    @Override
    public void startActivityForResult(@NonNull Class clazz, Bundle bundle, int requestCode) {
        if (isAdded() && getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).startActivityForResult(clazz, bundle, requestCode);
        }
    }

}

