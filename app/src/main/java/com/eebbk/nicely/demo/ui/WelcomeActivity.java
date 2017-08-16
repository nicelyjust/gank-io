package com.eebbk.nicely.demo.ui;

import android.content.Intent;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;
import com.eebbk.nicely.demo.utils.UiUtils;

/**
 * Created by nicely on 2017/8/16.
 */

public class WelcomeActivity extends BaseActivity  {
    @Override
    protected int getContentView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initWidget() {
        // TODO: 2017/8/17  是否第一次进入--》是，引导页；不是，广告页

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
