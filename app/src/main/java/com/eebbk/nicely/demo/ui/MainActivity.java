package com.eebbk.nicely.demo.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;
import com.eebbk.nicely.demo.bean.MockBean;
import com.eebbk.nicely.demo.utils.L;
import com.eebbk.nicely.demo.view.Spider;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo
 *  @文件名:   MainActivity
 *  @创建者:   lz
 *  @创建时间:  2017/7/19 18:07
 *  @描述：    TODO
 */
public class MainActivity extends BaseActivity implements View.OnClickListener, Spider.OnSpiderListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.subject_spider)
    Spider       mSubjectSpider;
    @BindView(R.id.rg)
    LinearLayout mRg;
    @BindView(R.id.main_container)
    FrameLayout  mMainContainer;
    private List<MockBean> mBeanList = new ArrayList<>();
    private MyHandler mHandler;
    private static class MyHandler extends Handler {

        private  WeakReference<MainActivity> mWeakReference;

        public MyHandler(MainActivity mainActivity) {
            this.mWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            mWeakReference.get().upData();
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        mSubjectSpider.setEnabled(false);
        mHandler = new MyHandler(this);
    }
    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    mockData();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void mockData() {
        if (mBeanList != null) {
            mBeanList.clear();
        }
        for (int i = 0; i < 10; i++) {
            mBeanList.add(new MockBean("科目"+ i  , i));
        }
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDismiss() {
        L.d(TAG , "popupwindow消失");
    }

    @Override
    public void onItemClick(int position) {
        L.d(TAG , "点击了" + position);
    }

    private void upData() {
        mSubjectSpider.setEnabled(true);
        mSubjectSpider.setData(mBeanList);
        mSubjectSpider.setOnSpiderListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PopupWindow popupWindow = mSubjectSpider.getPopupWindow();
        if ( popupWindow!= null) {
            popupWindow.dismiss();
            mSubjectSpider.setPopupWindow(null);
        }
    }
}
