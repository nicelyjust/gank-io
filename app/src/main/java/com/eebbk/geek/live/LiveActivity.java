package com.eebbk.geek.live;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eebbk.geek.R;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 *  @项目名：  EasyChart
 *  @包名：    com.github.iron.easychart
 *  @文件名:   LiveActivity
 *  @创建者:   lz
 *  @创建时间:  2019/11/20 18:34
 *  @描述：
 */
public class LiveActivity extends AppCompatActivity {

    private LiveHRView mLiveHRView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mLiveHRView = findViewById(R.id.hr_v);
        mLiveHRView.setZones(new int[]{56, 115, 145, 190}, new int[]{98, 117, 137, 156, 176, 195});

        final MyHandler handler = new MyHandler(this);
        final Random random = new Random();
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.arg1 = random.nextInt(190);
            handler.sendMessage(obtain);
        }, 1, 3, TimeUnit.SECONDS);
    }

    private static class MyHandler extends Handler {
        private static final String TAG = "MyHandler";
        private WeakReference<LiveActivity> weakReference;

        public MyHandler(LiveActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LiveActivity activity = weakReference.get();
            if (activity != null) {
                Log.d(TAG, "handleMessage: "+msg.arg1);
                activity.mLiveHRView.setLiveValue(msg.arg1);
            }
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LiveActivity.class);
        context.startActivity(starter);
    }
}
