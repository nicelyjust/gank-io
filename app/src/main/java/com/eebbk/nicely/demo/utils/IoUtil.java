package com.eebbk.nicely.demo.utils;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.utils
 *  @创建者:   lz
 *  @创建时间:  2017/9/12 21:48
 *  @修改时间:  nicely 2017/9/12 21:48
 *  @描述：    TODO
 */

import java.io.Closeable;
import java.io.IOException;

public final class IoUtil {
    private IoUtil() {
    }
    public static void close(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
