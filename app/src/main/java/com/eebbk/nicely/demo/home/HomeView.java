package com.eebbk.nicely.demo.home;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.home
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 20:21
 *  @修改时间:  nicely 2017/8/27 20:21
 *  @描述：    TODO
 */

import com.eebbk.nicely.demo.base.mvp.BaseView;
import com.eebbk.nicely.demo.bean.MockBean;

public interface HomeView extends BaseView{
    void render(MockBean mockBean);
}
