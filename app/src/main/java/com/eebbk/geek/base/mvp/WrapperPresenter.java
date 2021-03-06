package com.eebbk.geek.base.mvp;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.base.mvp
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 19:17
 *  @修改时间:  nicely 2017/8/27 19:17
 *  @描述：
 */

public abstract class WrapperPresenter<T extends BaseView> implements Presenter<T>
{

    public T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewNotNull() {
        return mView != null;
    }
}
