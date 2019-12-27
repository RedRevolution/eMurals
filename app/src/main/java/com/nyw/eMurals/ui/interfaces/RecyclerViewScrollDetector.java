package com.nyw.eMurals.ui.interfaces;

import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {

    public abstract void onScrollUp();

    public abstract void onScrollDown();
    
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy > 0) {
            onScrollUp();
        } else {
            onScrollDown();
        }
    }
}
