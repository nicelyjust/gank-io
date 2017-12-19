package com.eebbk.geek.news.view;

/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.news.view
 *  @文件名:   NewsHomeFragment
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 19:56
 *  @描述：    TODO
 */

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.news.adapter.NewsHomeAdapter;

import butterknife.BindView;

public class NewsHomeFragment extends LazyBaseFragment {
    @BindView(R.id.sliding_tabs)
    TabLayout mSlidingTab;
    @BindView(R.id.vp_news_home)
    ViewPager mVp;
    private NewsHomeAdapter mAdapter;

    public static NewsHomeFragment newInstance() {
        return new NewsHomeFragment();
    }
    @Override
    protected void fetchData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_home;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mAdapter = new NewsHomeAdapter(getChildFragmentManager());
        mVp.setAdapter(mAdapter);
        mVp.setOffscreenPageLimit(3);
        mSlidingTab.setupWithViewPager(mVp);
        mSlidingTab.setTabMode(TabLayout.MODE_FIXED);
    }
}
