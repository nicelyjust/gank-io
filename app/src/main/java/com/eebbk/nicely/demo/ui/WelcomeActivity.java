package com.eebbk.nicely.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.utils.UiUtils;

/**
 * Created by nicely on 2017/8/16.
 */

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
