package com.eebbk.nicely.demo.ui;

import android.view.View;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo
 *  @文件名:   MainActivity
 *  @创建者:   lz
 *  @创建时间:  2017/7/19 18:07
 *  @描述：    TODO
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {

    }

    @Override
    public void onClick(View v) {
    }
}
