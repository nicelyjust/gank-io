package com.eebbk.geek.module.news.view;

/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.module.news.view
 *  @文件名:   NewsView
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 17:36
 *  @描述：    TODO
 */

import com.eebbk.geek.base.mvp.BaseView;
import com.eebbk.geek.bean.netBean.DataInfoVo;

import java.util.List;

public interface NewsView extends BaseView{
    void loadData(List<DataInfoVo> dataInfoVos);

    void showPullRefreshOk();

    void loadDataMore(List<DataInfoVo> dataInfoVos);

    void showLoadMoreOk();
}
