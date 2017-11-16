package com.eebbk.geek.beauty;

import com.eebbk.geek.base.mvp.BaseView;
import com.eebbk.geek.bean.netBean.DataInfoVo;

import java.util.List;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   BeautyView
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 20:41
 *  @描述：
 */

public interface BeautyView extends BaseView {

    void loadData(List<DataInfoVo> dataInfoVoList);
}
