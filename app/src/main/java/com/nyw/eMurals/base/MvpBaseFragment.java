package com.nyw.eMurals.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class MvpBaseFragment<V,T extends BasePresenter<V>> extends BaseFragment {


    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建Presenter
        mPresenter = createPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(mPresenter != null){
            mPresenter.attachView((V)this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
    protected abstract int requestLayoutId();
    protected abstract T createPresenter();

}
