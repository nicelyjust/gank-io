package com.eebbk.geek.http;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.http
 *  @文件名:   ApiServer
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/13 20:49
 *  @描述：    接口请求实现类
 */

import com.eebbk.geek.constant.NetConst;
import com.eebbk.geek.http.api.ServerApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiServer {

    private static ServerApi sServerApi;

    public static void init() {
        Retrofit sRetrofit = new Retrofit.Builder()
                .baseUrl(NetConst.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        sServerApi = sRetrofit.create(ServerApi.class);

    }

    public static ServerApi getServerApi() {
        return sServerApi;
    }
}
