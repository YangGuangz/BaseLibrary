package com.egee.baselibrary.widget.viewpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Date: 2019/10/25 10:58
 * @Author: YGZ
 * @Description: ViewPager适配器
 * @Version:
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

}