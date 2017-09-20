package com.eebbk.nicely.demo.imageloader;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo
 *  @创建者:   lz
 *  @创建时间:  2017/9/12 20:58
 *  @修改时间:  nicely 2017/9/12 20:58
 *  @描述：    ImageLoader ImageCache
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private static ImageLoader INSTANSE;

    public static ImageLoader getInstanse() {
        if (INSTANSE == null){
            synchronized (ImageLoader.class) {
                if (INSTANSE == null){
                    INSTANSE = new ImageLoader();
                }
            }
        }
        return INSTANSE;
    }

    ImageCache mImageCache = new MemoryCache();
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 2);

    public void setImageCache(ImageCache imageCache) {
        this.mImageCache = imageCache;
    }

    public void displayImage(String url, ImageView imageView) {
        //  先从缓存找
        Bitmap bitmap = mImageCache.getBitmap(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        loadNetImage(url ,imageView);

    }

    private void loadNetImage(final String url, final ImageView imageView) {
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (url.equals(imageView.getTag())) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(url , bitmap);
            }
        });
    }

    private Bitmap downLoadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
