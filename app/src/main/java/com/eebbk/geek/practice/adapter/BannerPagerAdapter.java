package com.eebbk.geek.practice.adapter;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.practice.adapter
 *  @创建者:   lz
 *  @创建时间:  2018/7/8 17:01
 *  @修改时间:  nicely 2018/7/8 17:01
 *  @描述：    TODO
 */

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BannerPagerAdapter extends PagerAdapter{
    private Context mContext;
    private int[] mArrUrl;

    public BannerPagerAdapter(Context context, int[] resImg) {
        mContext = context;
        mArrUrl = resImg;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        position = position % mArrUrl.length;
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(mArrUrl[position]).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
