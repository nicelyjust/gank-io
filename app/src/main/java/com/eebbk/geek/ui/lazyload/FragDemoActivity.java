package com.eebbk.geek.ui.lazyload;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   FragDemoActivity
 *  @创建者:   lz
 *  @创建时间:  2017/11/1 10:44
 *  @修改时间:  Administrator 2017/11/1 10:44 
 *  @描述：
 */
public class FragDemoActivity extends BaseActivity {
    private static final String TAG = "FragDemoActivity";
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    @Override
    protected int getContentView() {
        return R.layout.activity_frag_demo;
    }

    @Override
    protected void initWidget() {
        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        mVp.setAdapter(pagerAdapter);
        mVp.setOffscreenPageLimit(2);
        mSlidingTab.setupWithViewPager(mVp);
        mSlidingTab.setTabMode(TabLayout.MODE_FIXED);
    }
}
