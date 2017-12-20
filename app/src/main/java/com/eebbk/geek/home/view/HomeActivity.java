package com.eebbk.geek.home.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseMvpAct;
import com.eebbk.geek.bean.MockBean;
import com.eebbk.geek.beauty.BeautyFragment;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.home.HomePresenterImpl;
import com.eebbk.geek.news.view.NewsHomeFragment;
import com.eebbk.geek.ui.lazyload.DemoFragment;
import com.eebbk.geek.utils.L;
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
 *  v4-24.0.0+ 开始，官方修复了fragment重叠的问题，
 */

public class HomeActivity extends BaseMvpAct<HomePresenterImpl> implements HomeView, OnTabSelectListener,
                                                                           OnTabReselectListener {

    @BindView(R.id.bottom_bar)
    BottomBar         mBottomBar;
    @BindView(R.id.home_container)
    FrameLayout mContainer;
    private NewsHomeFragment mNewsFragment;
    private DemoFragment mNoteFragment;
    private BeautyFragment mBeautyFragment;
    private DemoFragment mMineFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        dealWithFragment(savedInstanceState);
        mBottomBar.setOnTabSelectListener(this);
        mBottomBar.setOnTabReselectListener(this);
        BottomBarTab tweetBarTab = mBottomBar.getTabWithId(R.id.tab_tweet);
        tweetBarTab.setBadgeCount(1);
    }

    private void dealWithFragment(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            int pos = getIntent().getIntExtra("position", 1);
            mNewsFragment = NewsHomeFragment.newInstance();
            mNoteFragment = DemoFragment.newInstance("2");
            mBeautyFragment = BeautyFragment.newInstance(Constant.Category.IMAGE);
            mMineFragment = DemoFragment.newInstance("4");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.home_container , mNewsFragment ,"1");
            ft.add(R.id.home_container , mNoteFragment ,"2");
            ft.add(R.id.home_container , mBeautyFragment ,"3");
            ft.add(R.id.home_container , mMineFragment ,"4");
            // show hide 懒加载调用的是hidden
            ft.hide(mNewsFragment);
            ft.hide(mNoteFragment);
            ft.hide(mBeautyFragment);
            ft.hide(mMineFragment);
            switch (pos) {
                case 1:
                    ft.show(mNewsFragment);
                    break;
                case 2:
                    ft.show(mNoteFragment);
                    break;
                case 3:
                    ft.show(mBeautyFragment);
                    break;
                case 4:
                    ft.show(mMineFragment);
                    break;
            }
            ft.commit();
        } else {
            int pos = savedInstanceState.getInt("position", 1);
            mNewsFragment = (NewsHomeFragment) getSupportFragmentManager().findFragmentByTag("1");
            mNoteFragment = (DemoFragment) getSupportFragmentManager().findFragmentByTag("2");
            mBeautyFragment = (BeautyFragment) getSupportFragmentManager().findFragmentByTag("3");
            mMineFragment = (DemoFragment) getSupportFragmentManager().findFragmentByTag("4");

            showCurFragment(pos);
        }

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
    public void showMessage(String message) {

    }
    @Override
    public void showMessage(int message) {

    }
    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_news:
                showCurFragment(1);
                 break;
            case R.id.tab_tweet:
                showCurFragment(2);
                break;
            case R.id.tab_explore:
                showCurFragment(3);
                break;
            case R.id.tab_me:
                showCurFragment(4);
                break;
            default:
                 break;
        }

    }
    private void showCurFragment(int pos) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mNewsFragment);
        ft.hide(mNoteFragment);
        ft.hide(mBeautyFragment);
        ft.hide(mMineFragment);
        switch (pos) {
            case 1:
                ft.show(mNewsFragment);
                break;
            case 2:
                ft.show(mNoteFragment);
                break;
            case 3:
                ft.show(mBeautyFragment);
                break;
            case 4:
                ft.show(mMineFragment);
                break;
        }
        ft.commit();
    }
    @Override
    public void onTabReSelected(@IdRes int tabId) {
        mBottomBar.getTabWithId(tabId).removeBadge();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.Extra.DEMO_FOR_RESULT:
                    String stringExtra = data.getStringExtra(Constant.Extra.DEMO_POSITION);
                    L.d("lz" , "i got too :" + stringExtra);
                    break;
                default:
                    break;
            }
        }
    }
}
