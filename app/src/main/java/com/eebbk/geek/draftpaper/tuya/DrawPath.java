package com.eebbk.geek.draftpaper.tuya;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 13:08
 *  @修改时间:  nicely 2018/8/12 13:08
 *  @描述：    TODO
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class DrawPath implements IDraw{
    public Paint mPaint;
    public Path mPath;
    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mPath ,mPaint);
    }

    @Override
    public void undo(Canvas canvas) {

    }
}
