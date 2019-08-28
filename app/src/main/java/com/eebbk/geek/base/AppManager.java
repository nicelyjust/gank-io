package com.eebbk.geek.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.eebbk.geek.http.ApiServer;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.base
 *  @文件名:   AppManager
 *  @创建者:   lz
 *  @创建时间:  2017/8/11 15:11
 *  @描述：
 */
public class AppManager extends Application {
    private static final String TAG = "AppManager";
    private static Context sAppContext;
    private static Handler sHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
        sHandler = new Handler();
        ApiServer.init();
        //FlutterMain.startInitialization(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static Context getsAppContext() {
        return sAppContext;
    }

    public static Handler getsHandler() {
        return sHandler;
    }

}
