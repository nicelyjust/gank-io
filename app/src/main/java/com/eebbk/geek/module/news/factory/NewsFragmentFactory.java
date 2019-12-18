package com.eebbk.geek.module.news.factory;

/*
 *  @项目名：  gank-io 
 *  @包名：    com.eebbk.geek.module.news.factory
 *  @文件名:   NewsFragmentFactory
 *  @创建者:   Administrator
 *  @创建时间:  2017/12/18 20:28
 *  @描述：    Android | iOS | 休息视频 | 拓展资源 | 前端
 */

import androidx.fragment.app.Fragment;
import android.util.SparseArray;

import com.eebbk.geek.module.news.view.NewsFragment;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class NewsFragmentFactory {
    private static final String TAG = "NewsFragmentFactory";
    private static final int ANDROID = 0;
    private static final int IOS = 1;
    private static final int FRONT_END = 2;
    private static final int EXTRA_SOURCE = 3;
    private static final int VIDEOS = 4;
    private static SparseArray<Reference<Fragment>> mFragments = new SparseArray<>();

    public static Fragment createNewsFragment(int key){

        Fragment fragment = null;
        //优先从集合中取出来
        Reference<Fragment> fragmentReference = mFragments.get(key);
        if (fragmentReference != null && fragmentReference.get() != null) {
            fragment = fragmentReference.get();
            if (fragment != null) {
                return fragment;
            }
        }

        switch (key) {
            case ANDROID:
                fragment = NewsFragment.newInstance("Android");
                break;
            case IOS:
                fragment = NewsFragment.newInstance("iOS");

                break;
            case FRONT_END:
                fragment = NewsFragment.newInstance("前端");

                break;
            case EXTRA_SOURCE:
                fragment = NewsFragment.newInstance("拓展资源");

                break;
            case VIDEOS:
                fragment = NewsFragment.newInstance("休息视频");

                break;
            default:
                break;
        }
        //保存fragment到集合中
        mFragments.put(key , new WeakReference<>(fragment));

        return fragment;
    }
}
