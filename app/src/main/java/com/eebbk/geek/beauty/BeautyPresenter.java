package com.eebbk.geek.beauty;

import android.content.Context;

import com.eebbk.geek.base.mvp.WrapperPresenter;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.constant.Constant;

import java.util.List;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   BeautyPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 20:21
 *  @描述：    p层应该是纯java代码
 */

public class BeautyPresenter extends WrapperPresenter<BeautyView> implements IBeautyPresenter{
    private Context mContext;
    private  BeautyModel mBeautyModel;
    private  int mCurrentPage;
    private List<DataInfoVo> mDataInfoVoList;

    public BeautyPresenter(Context context) {
        mContext = context;
        mBeautyModel = new BeautyModel(this);
    }

    public void loadData(String category, int loadType) {
        if (mBeautyModel != null) {
            mBeautyModel.loadData(category , loadType ,mCurrentPage);
            if (loadType == Constant.LOAD_TYPE_UP) {
                mCurrentPage++;
            } else {
                mCurrentPage = 0;
            }
        }
    }

    @Override
    public void loadDataSuccess(List<DataInfoVo> dataInfoVoList, int type) {
        if (!isViewNotNull()) {
            return;
        }
        mDataInfoVoList = dataInfoVoList;
        mView.hideLoading();
        if (type == Constant.LOAD_TYPE_UP) {
            this.mDataInfoVoList.addAll(dataInfoVoList);
        } else {
            this.mDataInfoVoList = dataInfoVoList;
        }
        mView.loadData(dataInfoVoList);
    }

    @Override
    public void loadDataEmpty(int loadType) {
        if (isViewNotNull()) {
            mView.hideLoading();
        }
        if (loadType == Constant.LOAD_TYPE_NORMAL) {
            if (isViewNotNull()) {
            //  显示错误视图
                mView.showError("空的数据");
            }
        }
    }

    @Override
    public void loadDataFailed(int loadType, String msg) {
        if (isViewNotNull()) {
            mView.showError(msg);
        }
    }

    @Override
    public void destroy() {

    }
}
