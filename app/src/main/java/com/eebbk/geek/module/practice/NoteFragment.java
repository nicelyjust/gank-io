package com.eebbk.geek.module.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.module.practice.adapter.BannerPagerAdapter;
import com.eebbk.geek.module.practice.animate.AnimateActivity;
import com.eebbk.geek.module.practice.handler.HandlerActivity;
import com.eebbk.geek.module.practice.threadPool.ThreadPoolActivity;
import com.eebbk.geek.module.rxLearn.RxJavaActivity;
import com.eebbk.geek.service.BasicAndroidActivity;
import com.eebbk.geek.ui.H5Activity;
import com.eebbk.geek.utils.L;
import com.eebbk.geek.module.viewLearn.CustomViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  @项目名：  Demo
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   NoteFragment
 *  @创建者:   lz
 *  @创建时间:  2017/11/1 11:52
 *  @修改时间:  Administrator 2017/11/1 11:52
 *  @描述：
 */
public class NoteFragment extends LazyBaseFragment implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    private static final String TAG = "NoteFragment";
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.ll_dot_container)
    LinearLayout mLlDotContainer;
    @BindView(R.id.btn_js)
    Button mBtnJs;
    @BindView(R.id.btn_choose_picture)
    Button mBtnChoosePicture;

    @BindView(R.id.btn_rx)
    Button mBtnRxDemo;
    @BindView(R.id.btn_customization)
    Button mBtnCustomView;
    @BindView(R.id.btn_flutter)
    Button mBtnFlutter;
    @BindView(R.id.btn_android_basic)
    Button mBtnAndroidBasic;

    private int[] resImg = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
    private AutoCycleTask mAutoCycleTask;
    private int mPrePosition;
    private BannerPagerAdapter mAdapter;

    public static NoteFragment newInstance() {
        Bundle args = new Bundle();
        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 得到bundle
    }


    @Override
    protected void fetchData() {
        performViewPager();
        /* 实现 自动切换 */
        autoCycle();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void onResume() {
        super.onResume();
        L.d(TAG, "onResume: ");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.d(TAG, "onHiddenChanged: hidden" + hidden);
        if (hidden) {
            if (mAutoCycleTask != null)
                mAutoCycleTask.stop();
        } else {
            if (mAutoCycleTask != null)
                mAutoCycleTask.start();
        }
    }

    @Override
    protected void initWidget(View root) {
        performDot();
    }

    private void performDot() {
        for (int i = 0; i < resImg.length; i++) {
            View dotView = new View(mContext);
            dotView.setBackgroundResource(R.drawable.dot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            // 第一个原点不设置左边距
            if (i != 0) {
                params.leftMargin = 15;
            }
            dotView.setEnabled(false);
            dotView.setLayoutParams(params);
            mLlDotContainer.addView(dotView);
        }
    }

    private void performViewPager() {
        if (mAdapter == null) {
            mAdapter = new BannerPagerAdapter(mContext, resImg);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOnTouchListener(this);
        // Integer.MAX_VALUE / 2 有可能是任何item的位置 - 余数则让他和集合的pos=0的位置吻合
        int item = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % resImg.length);
        mViewPager.setCurrentItem(item);// 设置初始位置
        // 将第一个圆点设置选中的颜色
        mViewPager.setPageTransformer(true, new CustomTransformer());
        mLlDotContainer.getChildAt(mPrePosition).setEnabled(true);
    }

    private void autoCycle() {
        if (mAutoCycleTask == null) {
            mAutoCycleTask = new AutoCycleTask();
        }
        mAutoCycleTask.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPos = position % mLlDotContainer.getChildCount();
        mLlDotContainer.getChildAt(mPrePosition).setEnabled(false);
        mLlDotContainer.getChildAt(newPos).setEnabled(true);
        mPrePosition = newPos;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    @OnClick({R.id.btn_android_basic,R.id.btn_choose_picture,R.id.btn_js,R.id.btn_rx,R.id.btn_customization,
            R.id.btn_flutter,R.id.btn_thread_pool,R.id.btn_animate_example,R.id.btn_handler})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_android_basic:
                BasicAndroidActivity.start(mContext);
                break;
            case R.id.btn_choose_picture:
                break;
            case R.id.btn_js:
                Intent intent = new Intent(mContext, H5Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_rx:
                Intent intent2 = new Intent(mContext, RxJavaActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_customization:
                CustomViewActivity.start(mContext);
                break;
            case R.id.btn_flutter:
                //FlutterMainActivity.start(mContext);
                break;
            case R.id.btn_thread_pool:
                ThreadPoolActivity.start(mContext);
                break;
            case R.id.btn_animate_example:
                AnimateActivity.start(mContext);
                break;
            case R.id.btn_handler:
                HandlerActivity.start(mContext);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mAutoCycleTask.stop();
                break;
            case MotionEvent.ACTION_UP:
                mAutoCycleTask.start();
                break;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAutoCycleTask != null) {
            mAutoCycleTask.removeCallbacksAndMessages(null);
            mAutoCycleTask = null;
        }
    }
    @SuppressLint("HandlerLeak")
    class AutoCycleTask extends Handler implements Runnable {
        @Override
        public void run() {
            // 设置轮播下一图
            int currentItem = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++currentItem);
            postDelayed(this, 2000);
        }

        public void start() {
            postDelayed(this, 2000);
        }

        public void stop() {
            removeCallbacks(this);
        }
    }

    public class CustomTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.9F;

        @Override
        public void transformPage(View page, float position) {
            if (position < -1) {
                page.setScaleY(MIN_SCALE);
            } else if (position <= 1) {
                float scale = Math.max(MIN_SCALE, 1 - Math.abs(position));
                page.setScaleY(scale);

            } else {
                page.setScaleY(MIN_SCALE);
            }
        }

    }

}
