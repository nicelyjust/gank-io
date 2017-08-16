package com.eebbk.nicely.demo.utils;

import android.content.Context;
import android.os.Handler;

import com.eebbk.nicely.demo.base.AppManager;


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
