package com.eebbk.geek.beauty;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   IBeautyPresenter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/16 15:56
 *  @描述：
 */

import com.eebbk.geek.bean.netBean.DataInfoVo;

import java.util.List;

interface IBeautyPresenter {
    void loadDataSuccess(List<DataInfoVo> dataInfoVoList, int loadType);
    void loadDataEmpty(int loadType);
    void loadDataFailed(int loadType, String msg);
}
