package com.eebbk.geek.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.eebbk.geek.utils.UiUtils;
import com.eebbk.geek.R;

/**
 * Created by nicely on 2017/8/16.
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Splash_App_Theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 首先判断是否第一次进入app，--》出欢迎页
        // 再者做广告页逻辑判断，第一次下载广告到本地，用户下次进入是判断是否下载到本地：有则显示，否则直接进入进入resume
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 2017/8/27 做检查权限的操作
        UiUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        } , 1000);
    }
}
