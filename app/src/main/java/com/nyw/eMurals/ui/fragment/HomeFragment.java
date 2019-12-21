package com.nyw.eMurals.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.nyw.eMurals.R;
import com.nyw.eMurals.base.BaseFragment;
import com.nyw.eMurals.ui.adapter.HomePagerAdapter;

import butterknife.BindView;

/**
 * Created by yuxuehai on 17-11-29.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.app_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_container)
    ViewPager mHomeViewPager;

    private HomePagerAdapter mHomePagerAdapter;

    @Override
    public int requestLayoutId() {
        return R.layout.fragment_main_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomePagerAdapter = new HomePagerAdapter(getFragmentManager());
        mHomeViewPager.setAdapter(mHomePagerAdapter);
        mTabLayout.setupWithViewPager(mHomeViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentFactory.getFragmentFactory().clearView();
    }


}
