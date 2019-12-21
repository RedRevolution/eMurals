package com.nyw.eMurals.ui.view;


import com.nyw.eMurals.base.BaseView;
import com.nyw.eMurals.data.bean.PhotoInfo;

/**
 * Created by yuxuehai on 17-12-10.
 */

public interface PhotoView extends BaseView {
    void setPhotoInfo(PhotoInfo photoInfo);

    void finish();

    void sharePhotoes();

    void setWallPager();

    void showDialog();

    void dismissDialog();

    void setWallPagerSuccess();

    void setWallPagerFail();

    void startDownload();

    void putImage();
}
