package com.philong.danhsachphim.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.philong.danhsachphim.fragment.PopularMovieFragment;
import com.philong.danhsachphim.fragment.TopMovieFragment;

/**
 * Created by Long on 6/17/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments = {new TopMovieFragment(), new PopularMovieFragment()};
    private String[] mTitles = {"Top", "Popular"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
