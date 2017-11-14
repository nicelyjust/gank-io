package com.eebbk.geek.base.mvp;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.base.mvp
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 18:53
 *  @修改时间:  nicely 2017/8/27 18:53
 *  @描述：    与生命周期相关联，避免内存泄漏
 */

public interface Presenter<T extends BaseView> {
    // 释放资源的操作
    void destroy();

    void attachView(T view);

    void detachView();

}
