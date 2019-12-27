package com.nyw.eMurals.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class LayoutUtils {

    public static View inflate(Context context, int layoutId){
        if (layoutId <= 0) {
            return null;
        }
        return LayoutInflater.from(context).inflate(layoutId, null);
    }
}
