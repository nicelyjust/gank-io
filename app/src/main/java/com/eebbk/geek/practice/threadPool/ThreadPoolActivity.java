package com.eebbk.geek.practice.threadPool;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;
import com.eebbk.geek.utils.L;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.practice.threadPool
 *  @文件名:   ThreadPoolActivity
 *  @创建者:   lz
 *  @创建时间:  2019/9/30 10:25
 *  @描述：    线程池
 */
public class ThreadPoolActivity extends BaseActivity {
    private static final String TAG = "ThreadPoolActivity";
    @BindView(R.id.tv_show)
    TextView mTvShow;
    private MyRunnable mRunnable;

    @Override
    protected int getContentView() {
        return R.layout.activity_thread_pool;
    }

    @Override
    protected void initData() {
        mRunnable = new MyRunnable();
    }

    @OnClick({R.id.btn_customization, R.id.btn_fixed, R.id.btn_cached, R.id.btn_schedule, R.id.btn_single, R.id.tv_show})
    public void onViewClicked(View view) {
        ExecutorService service;
        switch (view.getId()) {
            case R.id.btn_customization:
                Future<Integer> submit = CustomThreadPool.getInstance().submit(mRunnable);
                try {
                    mTvShow.setText(String.format("结果: %d", submit.get()));
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_fixed:
                service = Executors.newFixedThreadPool(5);
                break;
            case R.id.btn_cached:
                service = Executors.newCachedThreadPool();
                break;
            case R.id.btn_schedule:
                ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
                break;
            case R.id.btn_single:
                service = Executors.newSingleThreadExecutor();
                break;
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadPoolActivity.class);
        context.startActivity(starter);
    }

    class MyRunnable implements Runnable, Callable<Integer> {

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            L.d(TAG,"run():" + Thread.currentThread());
        }

        @Override
        public Integer call() throws Exception {
            L.d(TAG,"call():" + Thread.currentThread());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5000;
        }
    }
}
