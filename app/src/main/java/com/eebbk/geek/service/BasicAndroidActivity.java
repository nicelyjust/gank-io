package com.eebbk.geek.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.service
 *  @文件名:   BasicAndroidActivity
 *  @创建者:   lz
 *  @创建时间:  2019/9/23 18:20
 *  @描述：
 */
public class BasicAndroidActivity extends BaseActivity {
    @BindView(R.id.btn_start)
    Button mBtnStart;
    @BindView(R.id.btn_bind)
    Button mBtnBind;
    @BindView(R.id.btn_stop)
    Button mBtnStop;

    @Override
    protected int getContentView() {
        return R.layout.activity_basic_android;
    }

    @Override
    protected void initWidget() {
    }

    @OnClick({R.id.btn_start, R.id.btn_bind, R.id.btn_stop})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this,LifeCycleService.class);
        switch (view.getId()) {
            case R.id.btn_start:
                startService(intent);
                break;
            case R.id.btn_bind:
                bindService(intent, mConnection, 0);
                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
