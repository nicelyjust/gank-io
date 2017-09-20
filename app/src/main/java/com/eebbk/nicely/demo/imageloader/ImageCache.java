package com.eebbk.nicely.demo.imageloader;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo
 *  @创建者:   lz
 *  @创建时间:  2017/9/12 21:00
 *  @修改时间:  nicely 2017/9/12 21:00
 *  @描述：    get
 */

import android.graphics.Bitmap;

public interface ImageCache {
    void put(String url, Bitmap bitmap);
    Bitmap getBitmap(String url);
}
