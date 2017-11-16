package com.eebbk.geek.home.adapter;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.home.adapter
 *  @文件名:   HomeAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/7 18:19
 *  @描述：
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eebbk.geek.home.ui.NewsFragment;
import com.eebbk.geek.ui.lazyload.DemoFragment;


public class HomeAdapter extends FragmentStatePagerAdapter{

    private NewsFragment mNewsFragment;
    private DemoFragment mDemoFragment1;
    private DemoFragment mDemoFragment2;
    private DemoFragment mDemoFragment3;

    public HomeAdapter(FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (mNewsFragment == null) {
                    mNewsFragment = NewsFragment.newInstance();
                }
                return mNewsFragment;
            case 1:
                if (mDemoFragment1 == null) {
                    mDemoFragment1 = DemoFragment.newInstance(String.valueOf(position + 1));
                }
                return mDemoFragment1;
            case 2:
                if (mDemoFragment2 == null) {
                    mDemoFragment2 = DemoFragment.newInstance(String.valueOf(position + 1));
                }
                return mDemoFragment2;
            case 3:
                if (mDemoFragment3 == null) {
                    mDemoFragment3 = DemoFragment.newInstance(String.valueOf(position + 1));
                }
                return mDemoFragment3;
        }
        System.exit(1);
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}