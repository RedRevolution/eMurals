package com.nyw.eMurals.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.transition.ChangeImageTransform;

import com.nyw.eMurals.R;
import com.nyw.eMurals.base.BaseActivity;
import com.nyw.eMurals.data.bean.UnsplashResult;
import com.nyw.eMurals.ui.fragment.PhotoesDetailFragment;
import com.nyw.eMurals.utils.Constants;


/**
 * Created by yuxuehai on 17-12-9.
 */

public class PhotoesDetailAcitivity extends BaseActivity {


    private PhotoesDetailFragment mPhotoDetailFragment;
    private UnsplashResult mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoes_detail);
        initView();
    }

    protected void initView() {
        if (Build.VERSION.SDK_INT >= 22) {
            getWindow().setSharedElementExitTransition(new ChangeImageTransform());
            getWindow().setSharedElementReenterTransition(new ChangeImageTransform());
        }
        Intent intent = getIntent();
        if (intent != null){
            mResult = (UnsplashResult) intent.getSerializableExtra(Constants.UNSPLASH_RESULT);
        }
        mPhotoDetailFragment = new PhotoesDetailFragment(mResult);
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container,mPhotoDetailFragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().beginTransaction()
                .remove(mPhotoDetailFragment);
    }
}
