package com.eebbk.geek.module.beauty.model;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.module.beauty
 *  @文件名:   BeautyModel
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/16 12:13
 *  @描述：    数据仓库,外边不关心你数据怎么来的
 */

import com.eebbk.geek.bean.netBean.DataInfo;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.module.beauty.p.IBeautyPresenter;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.http.ApiServer;
import com.eebbk.geek.utils.L;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BeautyModel {


    private IBeautyPresenter mPresenter;

    public BeautyModel(IBeautyPresenter presenter) {
        mPresenter = presenter;
    }

    public void loadData(String category, final int loadType , int currentPage) {
        ApiServer.getServerApi().getData(category, Constant.COUNT, currentPage)
                .subscribeOn(Schedulers.io()) // 订阅在子线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataInfo<List<DataInfoVo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataInfo<List<DataInfoVo>> value) {
                        if (value == null) {
                            mPresenter.loadDataFailed(loadType , Constant.DATA_EMPTY_MSG);
                        }else if (value.isError() || value.getResults() == null) {
                            mPresenter.loadDataFailed(loadType , Constant.DATA_EMPTY_MSG);
                        } else {
                            List<DataInfoVo> results = value.getResults();
                            if (results.isEmpty()) {
                                mPresenter.loadDataEmpty(loadType);
                            } else {
                                mPresenter.loadDataSuccess(results ,loadType);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresenter.loadDataFailed(loadType , e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        L.d("BeautyModel" , "onComplete()");
                    }
                });

    }
}
