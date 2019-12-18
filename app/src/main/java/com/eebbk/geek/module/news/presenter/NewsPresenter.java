package com.eebbk.geek.module.news.presenter;

import com.eebbk.geek.base.mvp.WrapperPresenter;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.module.news.model.NewsModel;
import com.eebbk.geek.module.news.view.NewsView;

import java.util.List;

import static com.eebbk.geek.constant.Constant.*;


/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.module.news.presenter
 *  @文件名:   NewsPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 17:36
 *  @描述：    没显示错误视图
 */

public class NewsPresenter extends WrapperPresenter<NewsView> implements INewsPresenter{

    private NewsModel mModel;
    private int mCurrentPage = 1;
    private long lastPullRefresh;
    private List<DataInfoVo> mDataInfoVoList;

    public NewsPresenter() {
        mModel = new NewsModel(this);
    }

    public void loadNewsData(int loadType, String category) {
        if (mModel != null) {
            if (loadType == LOAD_TYPE_UP) {
                mCurrentPage++;
            } else {
                mCurrentPage = 1;
            }
            mModel.loadNewsData(category , loadType , COUNT , mCurrentPage);
        }
    }

    @Override
    public void onLoadDataEmpty(int loadType) {
        if (!isViewNotNull()) {
            return;
        }
//        mView.hideLoading();
        mView.showLoadMoreOk();
        mView.showPullRefreshOk();
        if (loadType == Constant.LOAD_TYPE_NORMAL ) {
            //  显示空视图
            mView.showMessage("空的数据");
        }
    }

    @Override
    public void onLoadDataFailed(int loadType, String dataEmptyMsg) {
        if (!isViewNotNull()) {
            return;
        }
//        mView.hideLoading();
        mView.showLoadMoreOk();
        mView.showPullRefreshOk();
        mView.showMessage(dataEmptyMsg);
    }

    @Override
    public void onLoadDataSuccess(List<DataInfoVo> dataInfoVos, int loadType) {
        if (!isViewNotNull()) {
            return;
        }
        mView.hideLoading();
        if (loadType == LOAD_TYPE_UP) {
            this.mDataInfoVoList.addAll(dataInfoVos);
            mView.showLoadMoreOk();
            mView.loadDataMore( dataInfoVos);
            return;
        } else if (loadType == LOAD_TYPE_DOWN){
            this.mDataInfoVoList = dataInfoVos;
            mView.showPullRefreshOk();
        } else {
            this.mDataInfoVoList = dataInfoVos;
        }
        mView.loadData(mDataInfoVoList);
    }

    public void pullToRefresh(String category, boolean isLoadMore) {
        // 判断间隔时间
        if (!isLoadMore && System.currentTimeMillis() - lastPullRefresh < Constant.PULL_REFRESH_TIME) {
            mView.showPullRefreshOk();
            return;
        }
        if (!isLoadMore ) {
            lastPullRefresh = System.currentTimeMillis();
        }
        loadNewsData(isLoadMore ?  Constant.LOAD_TYPE_UP : Constant.LOAD_TYPE_DOWN, category);

    }
    @Override
    public void destroy() {
        if (mDataInfoVoList != null) {
            mDataInfoVoList.clear();
            mDataInfoVoList = null;
        }
    }
}
