package com.eebbk.geek.practice.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.AppManager;
import com.eebbk.geek.base.activities.BaseActivity;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.practice.handler
 *  @文件名:   HandlerActivity
 *  @创建者:   lz
 *  @创建时间:  2019/10/14 17:06
 *  @描述：    模拟股票实时询盘
 */
public class HandlerActivity extends BaseActivity {
    private static final int MSG_UPDATE_INFO = 0x01;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    Handler mCheckMsgHandler;
    private HandlerThread mCheckHandler;

    @Override
    protected int getContentView() {
        return R.layout.activity_handler;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCheckMsgHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCheckMsgHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void initWidget() {
        mCheckHandler = new HandlerThread("check-stack");
        mCheckHandler.start();
        mCheckMsgHandler = new Handler(mCheckHandler.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                mCheckMsgHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
            }
        };
    }

    @Override
    protected void initData() {

        OkHttpClient client = new OkHttpClient();
        //构造request
        Request request = new Request.Builder()
                .url("www.baidu.com")
                .build();

        try {
            Call call = client.newCall(request);
            Response execute = call.execute();
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
            ResponseBody body = execute.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkForUpdate() {
        try {
            Thread.sleep(1000);
            AppManager.getsHandler().post(() -> {
                String result = "今日大盘指数: <font color='red'>%d</font>";
                result = String.format(result, (int) (Math.random() * 3000 + 1000));
                mTvCount.setText(Html.fromHtml(result));
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCheckHandler.quit();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, HandlerActivity.class);
        context.startActivity(starter);
    }
}
