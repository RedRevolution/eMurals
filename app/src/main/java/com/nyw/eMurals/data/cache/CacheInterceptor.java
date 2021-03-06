package com.nyw.eMurals.data.cache;

import android.support.annotation.NonNull;


import com.nyw.eMurals.WallPagerApplications;
import com.nyw.eMurals.data.net.DownloadProgressResponseBody;
import com.nyw.eMurals.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (NetUtils.isNetworkAvailable(WallPagerApplications.getContext())) {
            Response response = chain.proceed(request);
            // read from cache for 0 s  有网络不会使用缓存数据
            int maxAge = 10;
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .body(new DownloadProgressResponseBody(response.body()))
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            //无网络时强制使用缓存数据
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Response response = chain.proceed(request);
//            int maxStale = 60;
            int maxStale = 60 * 60 * 24 * 3;
            return response.newBuilder()
                    .body(new DownloadProgressResponseBody(response.body()))
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}