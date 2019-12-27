package com.nyw.eMurals.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nyw.eMurals.ui.fragment.FragmentFactory;
import com.nyw.eMurals.utils.Constants;


public class HomePagerAdapter extends FragmentPagerAdapter {


    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragmentFactory().getFragment(position);
    }

    @Override
    public int getCount() {
        return Constants.DEFAULT_CHANNELS.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.DEFAULT_CHANNELS[position];
    }

}
