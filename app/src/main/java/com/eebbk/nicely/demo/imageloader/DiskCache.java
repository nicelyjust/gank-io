package com.eebbk.nicely.demo.imageloader;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo
 *  @创建者:   lz
 *  @创建时间:  2017/9/12 21:26
 *  @修改时间:  nicely 2017/9/12 21:26
 *  @描述：    TODO
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.eebbk.nicely.demo.utils.IoUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DiskCache implements ImageCache {
    static String cacheDir = "sdcard/cache/";

    public DiskCache() {
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
             outputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG , 100 , outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(outputStream);
        }
    }

    @Override
    public Bitmap getBitmap(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }
}
