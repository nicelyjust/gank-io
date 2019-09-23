package com.eebbk.geek.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.eebbk.geek.utils.L;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.service
 *  @文件名:   LifeCycleService
 *  @创建者:   lz
 *  @创建时间:  2019/9/23 18:03
 *  @描述：    两种启动方式
 */
public class LifeCycleService extends Service {
    private static final String TAG = "LifeCycleService";
    @Override
    public void onCreate() {
        super.onCreate();
        L.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
