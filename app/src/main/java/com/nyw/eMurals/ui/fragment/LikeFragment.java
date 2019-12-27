package com.nyw.eMurals.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nyw.eMurals.R;
import com.nyw.eMurals.base.MvpBaseFragment;
import com.nyw.eMurals.data.bean.UnsplashResult;
import com.nyw.eMurals.present.PhotoDisplayPresenter;
import com.nyw.eMurals.ui.view.DemoView;

import java.util.List;

import butterknife.BindView;

public class LikeFragment extends MvpBaseFragment<DemoView, PhotoDisplayPresenter> implements DemoView,View.OnClickListener {

    private static final String TAG = LikeFragment.class.getSimpleName();

    @BindView(R.id.empty_layout_like)
    RelativeLayout mEmptyLayout;
    @BindView(R.id.null_layout_like)
    RelativeLayout mNullLayout;
    @BindView(R.id.container_layout_like)
    RelativeLayout mContainerLayout;
    @BindView(R.id.swipe_layout_like)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle_like)
    RecyclerView mRecyclerView;
    @BindView(R.id.fb_totop_like)
    FloatingActionButton mActionButton;


    @Override
    protected int requestLayoutId() {
        return R.layout.fragment_like_layout;
    }

    @Override
    protected PhotoDisplayPresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoadView() {

    }

    @Override
    public void hideLoadView() {

    }

    @Override
    public void refreshData(List<UnsplashResult> unsplashResults) {

    }

    @Override
    public void addMoreData(List<UnsplashResult> unsplashResults) {

    }

    @Override
    public void showNoDataView() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void loadMoreError() {

    }

    @Override
    public void loadMoreEnd() {

    }

    @Override
    public void rollToTop() {

    }
}
