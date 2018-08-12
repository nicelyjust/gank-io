package com.eebbk.geek.draftpaper.tuya;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 12:55
 *  @修改时间:  nicely 2018/8/12 12:55
 *  @描述：    TODO
 */

import android.graphics.Path;

public class NormalBrush implements IBrush {
    @Override
    public void down(Path path, float x, float y) {
        path.moveTo(x , y);
    }

    @Override
    public void move(Path path, float x, float y) {
        path.lineTo(x , y);
    }

    @Override
    public void up(Path path, float x, float y) {

    }
}
