package com.nyw.eMurals.data.api;



import com.nyw.eMurals.data.bean.PhotoInfo;
import com.nyw.eMurals.data.bean.UnsplashResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yuxuehai on 17-12-2.
 */

public interface WallPagerApis {

    @GET("photos")
    Observable<List<UnsplashResult>> getRecentPhotos(@Query("client_id") String clientId, @Query("page") int page, @Query("per_page") int per_page, @Query("order_by") String order_by);

    @GET("photos")
    Observable<List<UnsplashResult>> getCuratedPhotos(@Query("client_id") String clientId, @Query("page") int page, @Query("per_page") int per_page, @Query("order_by") String order_by);

    @GET("photos/random")
    Observable<List<UnsplashResult>> getBuildingsPhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("count") int count);

    @GET("photos/random")
    Observable<List<UnsplashResult>> getFoodsPhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("count") int count);

    @GET("photos/random")
    Observable<List<UnsplashResult>> getNaturePhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("count") int count);

    @GET("photos/random")
    Observable<List<UnsplashResult>> getGoodsPhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("count") int count);

    @GET("photos/random")
    Observable<List<UnsplashResult>> getPersonPhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("count") int count);

    @GET("photos/random")
    Observable<List<UnsplashResult>> getTechnologyPhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("count") int count);

    //照片的信息 https://api.unsplash.com/photos/oCbrjDECdK0?client_id=eb54e3b9dc12b9e0862b028b646085355d20b3442fbdfca4633ca0f7b01ef9a6
    @GET("photos/{id}")
    Observable<PhotoInfo> getPhotoInfo(@Path("id") String id, @Query("client_id") String clientId);

    //下载图片api
    @GET
    Call<ResponseBody> downloadPicFromNet(@Url String url);
}
