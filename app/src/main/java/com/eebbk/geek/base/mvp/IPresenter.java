package com.eebbk.geek.base.mvp;

/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.base.mvp
 *  @文件名:   IPresenter
 *  @创建者:   lz
 *  @创建时间:  2017/12/18 17:33
 *  @描述：    TODO
 */

import java.util.List;

public interface IPresenter<T> {
    void onLoadDataEmpty(int loadType);
    void onLoadDataFailed(int loadType, String dataEmptyMsg);
    void onLoadDataSuccess(List<T> t , int loadType);
}
