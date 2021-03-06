package com.nyw.eMurals.present;

import android.content.Context;
import android.view.View;

import com.nyw.eMurals.R;
import com.nyw.eMurals.base.RxBasePresenter;
import com.nyw.eMurals.data.NetModel;
import com.nyw.eMurals.data.bean.UnsplashResult;
import com.nyw.eMurals.ui.view.DemoView;
import com.nyw.eMurals.utils.Constants;
import com.nyw.eMurals.utils.NetUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
public class PhotoDisplayPresenter extends RxBasePresenter<DemoView> {

    private NetModel mNetModel;

    public PhotoDisplayPresenter(Context context) {
        super(context);
        mNetModel = NetModel.getNetModel();
    }


    public boolean hasNetWork() {
        if (NetUtils.isNetworkAvailable(getContext())) {
            if (NetUtils.isWiFi(getContext())) {
                return true;
            }
        } else {
            return false;
        }

        return false;
    }

    public void requestDatas(String channel, int page) {
        Observable<List<UnsplashResult>> photoesData = getPhotoesData(channel, page);
        if (photoesData != null) {
            Subscription subscription = photoesData.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<UnsplashResult>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (getView() == null) {
                                return;
                            }
                            if (page == 1) {
                                getView().hideLoadView();
                                getView().showError(e.getMessage());
                            } else {
                                getView().loadMoreError();
                            }
                        }

                        @Override
                        public void onNext(List<UnsplashResult> unsplashResults) {
                            if (page == 1) {
                                if (unsplashResults.isEmpty()) {
                                    getView().showNoDataView();
                                } else {
                                    getView().hideLoadView();
                                    getView().refreshData(unsplashResults);
                                }
                            } else {
                                if (unsplashResults.isEmpty()) {
                                    getView().loadMoreEnd();
                                } else {
                                    getView().addMoreData(unsplashResults);
                                }
                            }
                        }
                    });
            mSubscriptions.add(subscription);
        }
    }

    private Observable<List<UnsplashResult>> getPhotoesData(String channel, int page) {
        switch (channel) {
            case Constants.CHANNLE_NEW:
                return mNetModel.getHttpHelper().getRecentPhotos(Constants.UNSPLASH_APP_KEY, page,
                        Constants.NUM_PER_PAGE, Constants.ORDER_BY_LATEST);

            case Constants.CHANNLE_PICK:
                return mNetModel.getHttpHelper().getCuratedPhotos(Constants.UNSPLASH_APP_KEY, page,
                        Constants.NUM_PER_PAGE, Constants.ORDER_BY_POPULAR);

            case Constants.CHANNLE_ARC:
                return mNetModel.getHttpHelper().getBuildingsPhotos(Constants.UNSPLASH_APP_KEY, "Buildings", 20);

            case Constants.CHANNLE_FOOD:
                return mNetModel.getHttpHelper().getFoodsPhotos(Constants.UNSPLASH_APP_KEY, "Food", 20);

            case Constants.CHANNLE_NATURE:
                return mNetModel.getHttpHelper().getNaturePhotos(Constants.UNSPLASH_APP_KEY, "Nature", 20);

            case Constants.CHANNLE_GOOD:
                return mNetModel.getHttpHelper().getGoodsPhotos(Constants.UNSPLASH_APP_KEY, "Goods", 20);

            case Constants.CHANNLE_PERSON:
                return mNetModel.getHttpHelper().getPersonPhotos(Constants.UNSPLASH_APP_KEY, "Person", 20);

            case Constants.CHANNLE_TECH:
                return mNetModel.getHttpHelper().getTechnologyPhotos(Constants.UNSPLASH_APP_KEY, "Technology", 20);
            default:
                break;
        }
        return null;
    }

    public void setClickEvent(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fb_totop:
                getView().rollToTop();
                break;
            case R.id.error_layout:
                getView().getData();
                break;
        }
    }
}
