package com.eebbk.geek.draftpaper.tuya;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 13:03
 *  @修改时间:  nicely 2018/8/12 13:03
 *  @描述：    对于
 */

import android.graphics.Canvas;

public interface IDraw {
    void draw(Canvas canvas);
    void undo(Canvas canvas);
}
