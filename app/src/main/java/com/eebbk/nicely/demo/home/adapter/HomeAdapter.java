package com.eebbk.nicely.demo.home.adapter;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.home.adapter
 *  @文件名:   HomeAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/7 18:19
 *  @描述：    TODO
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eebbk.nicely.demo.home.ui.NewsFragment;
import com.eebbk.nicely.demo.ui.lazyload.DemoFragment;

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
                mNewsFragment = NewsFragment.newInstance();
                return mNewsFragment;
            case 1:

                mDemoFragment1 = DemoFragment.newInstance(String.valueOf(position + 1));
                return mDemoFragment1;
            case 2:
                mDemoFragment2 = DemoFragment.newInstance(String.valueOf(position + 1));
                return mDemoFragment2;
            case 3:
                mDemoFragment3 = DemoFragment.newInstance(String.valueOf(position + 1));
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
