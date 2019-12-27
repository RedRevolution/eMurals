package com.nyw.eMurals;

import android.app.Application;
import android.content.Context;

public class WallPagerApplications extends Application {
    private static Context mContext;

    public static Context getContext() {
            return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mContext == null) {
            mContext = getApplicationContext();
        }
    }
}
