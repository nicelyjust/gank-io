package com.eebbk.nicely.demo.base.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        initWidget();
        initData();
    }

    /**
     * 请求数据,子类选择实现
     */
    protected void initData() {

    }

    /**
     * 初始化组件，设置监听器，子类选择实现
     */
    protected void initWidget() {

    }

    /**
     * 布局初始化，子类必须实现
     * @return
     */
    protected abstract int getContentView();
}
