package com.eebbk.geek.news.presenter;

import com.eebbk.geek.base.mvp.WrapperPresenter;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.news.model.NewsModel;
import com.eebbk.geek.news.view.NewsView;

import java.util.List;

import static com.eebbk.geek.constant.Constant.*;


/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.news.presenter
 *  @文件名:   NewsPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 17:36
 *  @描述：    TODO
 */

public class NewsPresenter extends WrapperPresenter<NewsView> implements INewsPresenter{

    private NewsModel mModel;
    private int mCurrentPage = 1;

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

    }

    @Override
    public void onLoadDataFailed(int loadType, String dataEmptyMsg) {

    }

    @Override
    public void onLoadDataSuccess(List<DataInfoVo> dataInfoVos, int loadType) {

    }


    @Override
    public void destroy() {

    }
}
