package com.nyw.eMurals.base;

import android.content.Context;

public abstract class BasePresenter<V> {


    private final Context mContext;
    protected V mViewRef;

    public BasePresenter(Context context) {
        mContext = context.getApplicationContext();
    }


    public Context getContext() {
        return mContext;
    }


    public void attachView(V view) {
        this.mViewRef = view;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef = null;
        }
    }

    protected V getView() {
        return mViewRef;
    }

}
