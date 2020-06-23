package com.ygz.baselibrary.widget.recyclerview.layoutmanager;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * @Date: 2019/7/24 15:02
 * @Author: YGZ
 * @Description: Fix RecyclerView Bug：IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter
 * @Version:
 */
public class WrapStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    public WrapStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public WrapStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}  