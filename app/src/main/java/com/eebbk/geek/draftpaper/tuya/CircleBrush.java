package com.eebbk.geek.draftpaper.tuya;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 12:57
 *  @修改时间:  nicely 2018/8/12 12:57
 *  @描述：    圆形笔触
 */

import android.graphics.Path;

public class CircleBrush implements IBrush {
    @Override
    public void down(Path path, float x, float y) {

    }

    @Override
    public void move(Path path, float x, float y) {
        path.addCircle(x ,y, 5 , Path.Direction.CW);
    }

    @Override
    public void up(Path path, float x, float y) {

    }
}
