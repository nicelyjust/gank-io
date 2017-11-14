package com.eebbk.geek.utils;

import android.content.Context;
import android.os.Handler;

import com.eebbk.geek.base.AppManager;


/**
 * Created by nicely on 2017/8/17.
 */

public class UiUtils {
    private UiUtils() {
    }
    public static Handler getHandler (){
        return  AppManager.getsHandler();
    }
    public static Context getApp (){
        return AppManager.getsAppContext();
    }
}
