package com.nyw.eMurals;

import android.app.Application;
import android.content.Context;

/**
 * Created by nyw on 18-1-7.
 */

public class WallPagerApplications extends Application {
    //// TODO: 17/12/14  
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
