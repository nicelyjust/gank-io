package com.eebbk.geek.live;

import android.graphics.Color;

/*
 *  @项目名：  mio_sport
 *  @包名：    com.lifesense.mio.module.live
 *  @文件名:   ColorGradient
 *  @创建者:   lz
 *  @创建时间:  2019/11/29 15:11
 *  @描述：两个颜色的渐变值算法工具类
 */
public class ColorGradient {

    /**
     * 计算从startColor过度到endColor过程中百分比为fraction时的颜色值
     * @param startColor 起始颜色 （格式#FFFFFFFF）
     * @param endColor 结束颜色 （格式#FFFFFFFF）
     * @param fraction 百分比0.5
     * @return 返回颜色值
     */
    public static int calculateColor(String startColor, String endColor, float fraction){

        int startAlpha = Integer.parseInt(startColor.substring(1, 3), 16);
        int startRed = Integer.parseInt(startColor.substring(3, 5), 16);
        int startGreen = Integer.parseInt(startColor.substring(5, 7), 16);
        int startBlue = Integer.parseInt(startColor.substring(7), 16);

        int endAlpha = Integer.parseInt(endColor.substring(1, 3), 16);
        int endRed = Integer.parseInt(endColor.substring(3, 5), 16);
        int endGreen = Integer.parseInt(endColor.substring(5, 7), 16);
        int endBlue = Integer.parseInt(endColor.substring(7), 16);

        int currentAlpha = (int) ((endAlpha - startAlpha) * fraction + startAlpha);
        int currentRed = (int) ((endRed - startRed) * fraction + startRed);
        int currentGreen = (int) ((endGreen - startGreen) * fraction + startGreen);
        int currentBlue = (int) ((endBlue - startBlue) * fraction + startBlue);

        String colorStr = "#" + getHexString(currentAlpha) + getHexString(currentRed)
                + getHexString(currentGreen) + getHexString(currentBlue);
        return Color.parseColor(colorStr);
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    public static String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
