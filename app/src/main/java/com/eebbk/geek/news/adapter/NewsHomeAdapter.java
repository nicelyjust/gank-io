package com.eebbk.geek.news.adapter;

/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.news
 *  @文件名:   NewsHomeAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 20:47
 *  @描述：
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.eebbk.geek.news.factory.NewsFragmentFactory;

public class NewsHomeAdapter extends FragmentStatePagerAdapter{
    private String tabTitles[] = new String[]{"Android","iOS","前端","拓展资源" , "休息视频"};

    public NewsHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragmentFactory.createNewsFragment(position);
    }

    @Override
    public int getCount() {
        return tabTitles == null ? 0 : tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
