package com.nyw.eMurals.data;


import com.nyw.eMurals.data.net.HttpHelperImpl;
import com.nyw.eMurals.data.net.HttpManage;

public class NetModel {

    private volatile static NetModel mNetModel;

    public HttpHelperImpl getHttpHelper() {
        return mHttpHelper;
    }

    private HttpHelperImpl mHttpHelper;

    public static NetModel getNetModel(){

        if (mNetModel == null){
            synchronized (NetModel.class){
                if (mNetModel == null){
                    mNetModel = new NetModel();
                }
            }
        }
        return mNetModel;
    }

    public NetModel(){
        //获取api类
        mHttpHelper = new HttpHelperImpl(HttpManage.getHttpManage().getWallPagerApis());
    }


}
