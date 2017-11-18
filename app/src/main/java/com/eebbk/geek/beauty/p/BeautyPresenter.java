package com.eebbk.geek.beauty.p;

import com.eebbk.geek.R;
import com.eebbk.geek.base.mvp.WrapperPresenter;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.beauty.BeautyView;
import com.eebbk.geek.beauty.model.BeautyModel;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.utils.TDevice;

import java.util.List;

import static com.eebbk.geek.constant.Constant.LOAD_TYPE_DOWN;
import static com.eebbk.geek.constant.Constant.LOAD_TYPE_UP;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   BeautyPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 20:21
 *  @描述：    p层应该是纯java代码
 */

public class BeautyPresenter extends WrapperPresenter<BeautyView> implements IBeautyPresenter {
    private BeautyModel      mBeautyModel;
    private int              mCurrentPage = 1;
    private List<DataInfoVo> mDataInfoVoList;
    private long lastPullRefresh;
    public BeautyPresenter() {
        mBeautyModel = new BeautyModel(this);
    }

    public void loadData(String category, int loadType) {
        if (!TDevice.hasInternet()) {
            mView.showMessage(R.string.no_internet);
            return;
        }
        if (mBeautyModel != null) {
            if (loadType == LOAD_TYPE_UP) {
                mCurrentPage++;
            } else {
                mCurrentPage = 1;
            }
            mBeautyModel.loadData(category , loadType ,mCurrentPage);
        }
    }

    /**
     * 加载更多与下拉刷新
     */
    public void pullToRefresh(String category, boolean isLoadMore) {
        // 判断间隔时间
        if (!isLoadMore && System.currentTimeMillis() - lastPullRefresh < Constant.PULL_REFRESH_TIME) {
            mView.showPullRefreshOk();
            return;
        }
        lastPullRefresh = System.currentTimeMillis();
        loadData(category , isLoadMore ? LOAD_TYPE_UP : Constant.LOAD_TYPE_DOWN);
    }

    @Override
    public void loadDataSuccess(List<DataInfoVo> dataInfoVoList, int type) {
        if (!isViewNotNull()) {
            return;
        }
        mView.hideLoading();
        if (type == LOAD_TYPE_UP) {
            this.mDataInfoVoList.addAll(dataInfoVoList);
            mView.showLoadMoreOk();
        } else if (type == LOAD_TYPE_DOWN){
            this.mDataInfoVoList = dataInfoVoList;
            mView.showPullRefreshOk();
        } else {
            this.mDataInfoVoList = dataInfoVoList;
        }
        mView.loadData(mDataInfoVoList);
    }

    @Override
    public void loadDataEmpty(int loadType) {
        if (!isViewNotNull()) {
            return;
        }
        mView.hideLoading();
        mView.showLoadMoreOk();
        mView.showPullRefreshOk();
        if (loadType == Constant.LOAD_TYPE_NORMAL ) {
            //  显示空视图
            mView.showMessage("空的数据");
        }
    }

    @Override
    public void loadDataFailed(int loadType, String msg) {
        if (!isViewNotNull()) {
            return;
        }
        mView.showLoadMoreOk();
        mView.showPullRefreshOk();
        mView.showMessage(msg);
    }

    @Override
    public void destroy() {

    }
}
