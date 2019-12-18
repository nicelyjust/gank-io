package com.eebbk.geek.module.practice.animate;

import android.animation.TypeEvaluator;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.viewLearn.hencoderpracticedraw1.practice
 *  @文件名:   PointSinEvaluator
 *  @创建者:   lz
 *  @创建时间:  2019/10/9 16:26
 *  @描述：
 */
public class PointSinEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());

        float y = (float) (Math.sin(x * Math.PI / 180) * 100) + endPoint.getY() / 2;
        Point point = new Point(x, y);
        return point;
    }
}
