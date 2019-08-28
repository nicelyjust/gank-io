package com.eebbk.geek.base.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

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

    protected RequestManager getImageLoader(){
        return Glide.with(this);
    }
}
