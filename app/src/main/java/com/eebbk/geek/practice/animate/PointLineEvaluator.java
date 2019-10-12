package com.eebbk.geek.practice.animate;

import android.animation.TypeEvaluator;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.practice.animate
 *  @文件名:   PointLineEvaluator
 *  @创建者:   lz
 *  @创建时间:  2019/10/12 14:57
 *  @描述：
 */
public class PointLineEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float startValueX = startValue.getX();
        float startValueY = startValue.getY();
        float x = startValueX + fraction *(endValue.getX()-startValueX);
        float y = startValueY + fraction * (endValue.getX() - startValueY);
        return new Point(x,y);
    }
}
