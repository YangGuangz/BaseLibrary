package com.egee.baselibrary.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.egee.baselibrary.R;
import com.egee.baselibrary.widget.recyclerview.brvah.BrvahLoadMoreView;
import com.egee.baselibrary.widget.recyclerview.layoutmanager.WrapLinearLayoutManager;

/**
 * @Date: 2019/10/16 14:14
 * @Author: YGZ
 * @Description: 列表类型或者多状态切换页面基类
 * @Version:
 */
public abstract class BaseMutilStatusMvpActivity<P extends BasePresenter, M extends IBaseModel> extends BaseMvpActivity<P, M> implements IBaseMutilStatusMvpView {

    /**
     * RecyclerView布局管理器，LinearLayoutManager
     */
    protected RecyclerView.LayoutManager mLinearLayoutManager;
    /**
     * 加载更多View
     */
    protected LoadMoreView mLoadMoreView;

    private View mNormalView;

    private FrameLayout mFlAbnormalContainer;
    /**
     * 加载中View
     */
    protected View mLoadingView;
    /**
     * 空数据View
     */
    protected View mEmptyView;
    /**
     * 错误View
     */
    protected View mErrorView;

    protected abstract View getNormalView();

    @Override
    public void initView() {
        super.initView();
        mNormalView = getNormalView();
        mFlAbnormalContainer = findViewById(R.id.fl_abnormal_container);

        // 初始化LinearLayoutManager
        mLinearLayoutManager = new WrapLinearLayoutManager(mContext);
        // 初始化加载更多View
        mLoadMoreView = new BrvahLoadMoreView();

        // 初始化加载中View
        mLoadingView = getLayoutInflater().inflate(R.layout.load_loading, null, false);
        // 初始化空数据View
        mEmptyView = getLayoutInflater().inflate(R.layout.empty_view, null, false);
        // 初始化错误View
        mErrorView = getLayoutInflater().inflate(R.layout.load_error, null, false);

        // 初始化点击重试
        TextView tvRetry = mErrorView.findViewById(R.id.tv_error);
        // 设置点击事件监听，调用retry()方法
        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retry();
            }
        });
    }

    /**
     * 显示加载中
     */
    @Override
    public void showLoading() {
        if (mNormalView == null || mFlAbnormalContainer == null || mLoadingView == null) {
            return;
        }
        mFlAbnormalContainer.removeAllViews();
        mFlAbnormalContainer.addView(mLoadingView);
        mNormalView.setVisibility(View.INVISIBLE);
        mFlAbnormalContainer.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载中
     */
    @Override
    public void hideLoading() {
        if (mNormalView == null || mFlAbnormalContainer == null) {
            return;
        }
        mFlAbnormalContainer.setVisibility(View.INVISIBLE);
        mNormalView.setVisibility(View.VISIBLE);
        mFlAbnormalContainer.removeAllViews();
    }

    /**
     * 显示空数据
     */
    @Override
    public void showEmpty() {
        if (mNormalView == null || mFlAbnormalContainer == null || mEmptyView == null) {
            return;
        }
        mFlAbnormalContainer.removeAllViews();
        mFlAbnormalContainer.addView(mEmptyView);
        mNormalView.setVisibility(View.INVISIBLE);
        mFlAbnormalContainer.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏空数据
     */
    @Override
    public void hideEmpty() {
        if (mNormalView == null || mFlAbnormalContainer == null) {
            return;
        }
        mFlAbnormalContainer.setVisibility(View.INVISIBLE);
        mNormalView.setVisibility(View.VISIBLE);
        mFlAbnormalContainer.removeAllViews();
    }

    /**
     * 显示失败
     */
    @Override
    public void showError() {
        if (mNormalView == null || mFlAbnormalContainer == null || mErrorView == null) {
            return;
        }
        mFlAbnormalContainer.removeAllViews();
        mFlAbnormalContainer.addView(mErrorView);
        mNormalView.setVisibility(View.INVISIBLE);
        mFlAbnormalContainer.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏失败
     */
    @Override
    public void hideError() {
        if (mNormalView == null || mFlAbnormalContainer == null) {
            return;
        }
        mFlAbnormalContainer.setVisibility(View.INVISIBLE);
        mNormalView.setVisibility(View.VISIBLE);
        mFlAbnormalContainer.removeAllViews();
    }

    /**
     * 点击重试
     */
    @Override
    public void retry() {

    }

}
