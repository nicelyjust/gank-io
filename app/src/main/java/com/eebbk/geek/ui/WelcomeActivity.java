package com.eebbk.geek.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eebbk.geek.home.view.HomeActivity;
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
        UiUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this , HomeActivity.class);
                startActivity(intent);
                finish();
            }
        } , 1000);
    }
}
