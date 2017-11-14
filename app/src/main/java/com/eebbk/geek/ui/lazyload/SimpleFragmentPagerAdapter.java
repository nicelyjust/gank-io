package com.eebbk.geek.ui.lazyload;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/*
 *  @项目名：  Demo
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   SimpleFragmentPagerAdapter
 *  @创建者:   lz
 *  @创建时间:  2017/11/1 14:17
 *  @修改时间:  Administrator 2017/11/1 14:17
 *  @描述：
 */
public class SimpleFragmentPagerAdapter extends FragmentStatePagerAdapter{
    private static final String TAG = "SimpleFragmentPagerAdapter";
    private String tabTitles[] = new String[]{"新闻","科技","娱乐","小视频"};
    public SimpleFragmentPagerAdapter(FragmentManager fm ) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DemoFragment.newInstance(String.valueOf(position));
    }

    @Override
    public int getCount() {
        return tabTitles != null ? tabTitles.length : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
