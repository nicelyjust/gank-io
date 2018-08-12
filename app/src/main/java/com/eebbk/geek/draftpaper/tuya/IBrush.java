package com.eebbk.geek.draftpaper.tuya;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 12:50
 *  @修改时间:  nicely 2018/8/12 12:50
 *  @描述：    画笔抽象接口
 */

import android.graphics.Path;

public interface IBrush {
    /**
     * 触点落下时
     * @param path 路径对象
     * @param x
     * @param y
     */
    void down(Path path , float x , float y);

    void move(Path path , float x , float y);

    void up(Path path , float x , float y);
}
