package com.eebbk.nicely.demo.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import com.bilibili.magicasakura.utils.ThemeUtils;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.base
 *  @文件名:   AppManager
 *  @创建者:   lz
 *  @创建时间:  2017/8/11 15:11
 *  @描述：    TODO
 */
public class AppManager extends Application implements ThemeUtils.switchColor{
    private static final String TAG = "AppManager";
    private static Context sAppContext;
    private static Handler sHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化组件
        sAppContext = getApplicationContext();
        sHandler = new Handler();
        ThemeUtils.setSwitchColor(this);
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

    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        return 0;
    }

    @Override
    public int replaceColor(Context context, @ColorInt int color) {
        return 0;
    }
}
