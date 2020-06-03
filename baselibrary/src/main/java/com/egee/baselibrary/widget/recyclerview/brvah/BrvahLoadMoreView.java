package com.egee.baselibrary.widget.recyclerview.brvah;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.egee.baselibrary.R;

/**
 * @Date: 2019/8/9 16:36
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public final class BrvahLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.load_brvah_loadmore;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

}
