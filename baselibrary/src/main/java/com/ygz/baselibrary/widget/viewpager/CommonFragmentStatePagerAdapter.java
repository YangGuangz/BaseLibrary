package com.ygz.baselibrary.widget.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @Date: 2019/11/7 11:16
 * @Author: YGZ
 * @Description:
 * @Version:
 */
public class CommonFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public CommonFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

}