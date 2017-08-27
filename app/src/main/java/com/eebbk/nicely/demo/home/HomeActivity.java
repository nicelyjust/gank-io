package com.eebbk.nicely.demo.home;

import android.content.Context;

import com.eebbk.nicely.demo.base.activities.BaseMvpAct;
import com.eebbk.nicely.demo.bean.MockBean;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 20:15
 *  @修改时间:  nicely 2017/8/27 20:15
 *  @描述：    TODO
 */

public class HomeActivity extends BaseMvpAct<HomePresenterImpl> implements HomeView{

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected HomePresenterImpl createP(Context context) {
        return null;
    }

    @Override
    public void render(MockBean mockBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }
}
