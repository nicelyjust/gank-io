package com.eebbk.geek.module.practice.animate;

import android.animation.TypeEvaluator;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.practice.animate
 *  @文件名:   ColorEvaluator
 *  @创建者:   lz
 *  @创建时间:  2019/10/12 17:51
 *  @描述：    TODO
 */
public class ColorEvaluator implements TypeEvaluator<String> {
    private int mCurrentRed = -1;

    private int mCurrentGreen = -1;

    private int mCurrentBlue = -1;

    @Override
    public String evaluate(float fraction, String startValue, String endValue) {
        System.out.println("startValue: " + startValue + ";endValue: " + endValue + "; fraction: " +fraction);
        int startRed = Integer.parseInt(startValue.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startValue.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startValue.substring(5, 7), 16);
        int endRed = Integer.parseInt(endValue.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endValue.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endValue.substring(5, 7), 16);
        // 初始化颜色的值
        if (mCurrentRed == -1) {
            mCurrentRed = startRed;
        }
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }
        // 计算初始颜色和结束颜色之间的差值
        int redDiff = Math.abs(startRed - endRed);
        int greenDiff = Math.abs(startGreen - endGreen);
        int blueDiff = Math.abs(startBlue - endBlue);
        int colorDiff = redDiff + greenDiff + blueDiff;
        if (mCurrentRed != endRed) {
            mCurrentRed = getCurrentColor(startRed, endRed, colorDiff, 0,
                    fraction);
        } else if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDiff,
                    redDiff, fraction);
        } else if (mCurrentBlue != endBlue) {
            mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDiff,
                    redDiff + greenDiff, fraction);
        }
        // 将计算出的当前颜色的值组装返回
        String str = "#FF" + getHexString(mCurrentRed)
                + getHexString(mCurrentGreen) + getHexString(mCurrentBlue);
        System.out.println(str);
        return str;
    }

    /**
     * 根据fraction值来计算当前的颜色。
     */
    private int getCurrentColor(int startColor, int endColor, int colorDiff,
                                int offset, float fraction) {
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * colorDiff - offset));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor + (fraction * colorDiff - offset));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
