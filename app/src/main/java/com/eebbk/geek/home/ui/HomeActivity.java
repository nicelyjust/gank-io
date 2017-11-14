package com.eebbk.geek.home.ui;

import android.content.Context;
import android.support.annotation.IdRes;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseMvpAct;
import com.eebbk.geek.bean.MockBean;
import com.eebbk.geek.home.HomePresenterImpl;
import com.eebbk.geek.home.HomeView;
import com.eebbk.geek.home.adapter.HomeAdapter;
import com.eebbk.geek.view.NoScrollViewPager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @创建者:   lz
 *  @创建时间:  2017/8/27 20:15
 *  @修改时间:  nicely 2017/8/27 20:15
 *  @描述：    选用bottombar,备选 : https://github.com/yingLanNull/AlphaTabsIndicator(未读消息,平板应用等适用性更强)
 */

public class HomeActivity extends BaseMvpAct<HomePresenterImpl> implements HomeView, OnTabSelectListener,
                                                                           OnTabReselectListener {

    @BindView(R.id.bottom_bar)
    BottomBar         mBottomBar;
    @BindView(R.id.vp_home)
    NoScrollViewPager mViewPager;

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initWidget() {
        mBottomBar.setOnTabSelectListener(this);
        mBottomBar.setOnTabReselectListener(this);
        BottomBarTab tweetBarTab = mBottomBar.getTabWithId(R.id.tab_tweet);
        tweetBarTab.setBadgeCount(9);
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);
    }

    @Override
    protected HomePresenterImpl createP(Context context) {
        return null;
    }

    @Override
    public void render(MockBean mockBean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_news:
                mViewPager.setCurrentItem(0);
//                Toast.makeText(AppManager.getsAppContext(), "news", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.tab_tweet:
                mViewPager.setCurrentItem(1);
//                Toast.makeText(AppManager.getsAppContext(),"tweet" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_explore:
                mViewPager.setCurrentItem(2);
//                Toast.makeText(AppManager.getsAppContext(), "explore", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_me:
                mViewPager.setCurrentItem(3);
//                Toast.makeText(AppManager.getsAppContext(), "me", Toast.LENGTH_SHORT).show();
                break;
            default:
                 break;
        }

    }

    @Override
    public void onTabReSelected(@IdRes int tabId) {
        mBottomBar.getTabWithId(tabId).removeBadge();
    }
}
