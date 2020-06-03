package com.egee.baselibrary.widget.tablayout;

import com.google.android.material.tabs.TabLayout;

/**
 * @Date: 2019/7/30 19:04
 * @Author: YGZ
 * @Description: OnTabSelectedListener包装类
 * @Version:
 */
public class OnTabSelectedListenerWrap implements TabLayout.OnTabSelectedListener {

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        onTabSelectedWrap(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        onTabUnselectedWrap(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        onTabReselectedWrap(tab);
    }

    public void onTabSelectedWrap(TabLayout.Tab tab) {

    }

    public void onTabUnselectedWrap(TabLayout.Tab tab) {

    }

    public void onTabReselectedWrap(TabLayout.Tab tab) {

    }

}