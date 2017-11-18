package com.eebbk.geek.base.mvp;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.base.mvp
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 18:50
 *  @修改时间:  nicely 2017/8/27 18:50
 *  @描述：    TODO
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showMessage(int message);

}
