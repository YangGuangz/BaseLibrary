package com.ygz.baselibrary.widget.viewpager;

import androidx.viewpager.widget.ViewPager;

/**
 * @Date: 2019/11/7 11:45
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class OnPageChangeListenerWrap implements ViewPager.OnPageChangeListener {

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        onPageScrolledWrap(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        onPageSelectedWrap(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        onPageScrollStateChangedWrap(state);
    }

    public void onPageScrolledWrap(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelectedWrap(int position) {

    }

    public void onPageScrollStateChangedWrap(int state) {

    }

}
