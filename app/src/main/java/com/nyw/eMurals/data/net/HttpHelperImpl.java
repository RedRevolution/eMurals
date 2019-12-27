package com.nyw.eMurals.data.net;


import com.nyw.eMurals.data.api.WallPagerApis;
import com.nyw.eMurals.data.bean.PhotoInfo;
import com.nyw.eMurals.data.bean.UnsplashResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

public class HttpHelperImpl{
    private WallPagerApis mWallPagerApis;

    public HttpHelperImpl(WallPagerApis apis) {
        this.mWallPagerApis = apis;
    }


    public Observable<List<UnsplashResult>> getRecentPhotos(String clientId, int page, int per_page, String order_by) {
        return mWallPagerApis.getRecentPhotos(clientId, page, per_page, order_by);
    }

    public Observable<List<UnsplashResult>> getCuratedPhotos(String clientId, int page, int per_page, String order_by) {
        return mWallPagerApis.getCuratedPhotos(clientId, page, per_page, order_by);
    }

    public Observable<List<UnsplashResult>> getBuildingsPhotos(String clientId, String query, int count) {
        return mWallPagerApis.getBuildingsPhotos(clientId, query, count);
    }

    public Observable<List<UnsplashResult>> getFoodsPhotos(String clientId, String query, int count) {
        return mWallPagerApis.getFoodsPhotos(clientId, query, count);
    }

    public Observable<List<UnsplashResult>> getNaturePhotos(String clientId, String query, int count) {
        return mWallPagerApis.getNaturePhotos(clientId, query, count);
    }

    public Observable<List<UnsplashResult>> getGoodsPhotos(String clientId, String query, int count) {
        return mWallPagerApis.getGoodsPhotos(clientId, query, count);
    }

    public Observable<List<UnsplashResult>> getPersonPhotos(String clientId, String query, int count) {
        return mWallPagerApis.getPersonPhotos(clientId, query, count);
    }

    public Observable<List<UnsplashResult>> getTechnologyPhotos(String clientId, String query, int count) {
        return mWallPagerApis.getTechnologyPhotos(clientId, query, count);
    }

    public Observable<PhotoInfo> getPhotoInfo(String id, String clienId) {
        return mWallPagerApis.getPhotoInfo(id, clienId);
    }

    private Call<ResponseBody> downloadPicFromNet(String url){
        return mWallPagerApis.downloadPicFromNet(url);
    }

    public void  downloadPicFromNet(String url, DownLoadCallback callback){
        Call<ResponseBody> call = downloadPicFromNet(url);
        call.enqueue(callback);

    }
}
