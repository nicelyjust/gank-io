package com.eebbk.nicely.demo.imageloader;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo
 *  @创建者:   lz
 *  @创建时间:  2017/9/12 21:09
 *  @修改时间:  nicely 2017/9/12 21:09
 *  @描述：    TODO
 */

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getHeight() * bitmap.getRowBytes() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mLruCache.put(url, bitmap);
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mLruCache.get(url);
    }
}
