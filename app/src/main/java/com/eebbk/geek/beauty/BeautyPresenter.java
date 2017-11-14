package com.eebbk.geek.beauty;

import android.content.Context;

import com.eebbk.geek.base.mvp.WrapperPresenter;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   BeautyPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 20:21
 *  @描述：    TODO
 */

public class BeautyPresenter extends WrapperPresenter<BeautyView> {
    private Context mContext;

    public BeautyPresenter(Context context) {

        mContext = context;
    }

    @Override
    public void destroy() {

    }
}
