package com.eebbk.geek.news.model;

/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.news.model
 *  @文件名:   NewsModel
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 17:32
 *  @描述：    TODO
 */

import com.eebbk.geek.bean.netBean.DataInfo;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.http.ApiServer;
import com.eebbk.geek.news.presenter.INewsPresenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsModel {
    private INewsPresenter mPresenter;

    public NewsModel(INewsPresenter presenter) {
        this.mPresenter = presenter;
    }

    public void loadNewsData(String category , final int loadType, int count , int currentPage) {
        ApiServer.getServerApi().getData(category, count, currentPage)
                // 订阅在子线程
                .subscribeOn(Schedulers.io())
                // 消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataInfo<List<DataInfoVo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataInfo<List<DataInfoVo>> value) {
                        if (value == null) {
                            mPresenter.onLoadDataFailed(loadType , Constant.DATA_EMPTY_MSG);
                        }else if (value.isError() || value.getResults() == null) {
                            mPresenter.onLoadDataFailed(loadType , Constant.DATA_EMPTY_MSG);
                        } else {
                            List<DataInfoVo> results = value.getResults();
                            if (results.isEmpty()) {
                                mPresenter.onLoadDataEmpty(loadType);
                            } else {
                                mPresenter.onLoadDataSuccess(results ,loadType);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
