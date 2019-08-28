package com.eebbk.geek.base.activities;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.base.activities
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 19:51
 *  @修改时间:  nicely 2017/8/27 19:51
 *  @描述：    TODO
 */

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.eebbk.geek.base.mvp.BaseView;
import com.eebbk.geek.base.mvp.WrapperPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class  BaseMvpAct<T extends WrapperPresenter> extends AppCompatActivity implements BaseView {
    private T mPresenter ;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mBind = ButterKnife.bind(this);
        mPresenter = createP(this);
        initWidget(savedInstanceState);
        initData();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 请求数据,子类选择实现
     */
    protected void initData() {

    }

    /**
     * 初始化组件，设置监听器，子类选择实现
     * @param savedInstanceState
     */
    protected void initWidget(Bundle savedInstanceState) {

    }

    /**
     * 布局初始化，子类必须实现
     * @return resId
     */
    protected abstract int getContentView();
    /**
     * 请求数据,子类选择实现
     */
    protected abstract T createP(Context context);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mBind != null) {
            mBind.unbind();
        }
    }
}
