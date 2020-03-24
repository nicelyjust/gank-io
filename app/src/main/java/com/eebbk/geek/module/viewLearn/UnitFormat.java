package com.eebbk.geek.module.viewLearn;

import android.annotation.SuppressLint;

/*
 *  @项目名：  EasyChart
 *  @包名：    com.github.iron.easychart
 *  @文件名:   UnitFormat
 *  @创建者:   lz
 *  @创建时间:  2020/3/19 14:39
 *  @描述：
 */
public class UnitFormat {
    /**
     *
     * @param value 时长
     * @return 00:00:00 or 00:00
     */
    @SuppressLint("DefaultLocale")
    public static String formatLiveDuration(int value) {
        int hours = value / 3600;
        int minutes = value % 3600 / 60;
        int seconds = value % 3600 % 60;
        String fHours = String.format("%02d", hours);
        String fMinutes = String.format("%02d", minutes);
        String fSeconds = String.format("%02d", seconds);

        if (fHours.equals("00"))
            return fMinutes + ":" + fSeconds;
        else
            return fHours + ":" + fMinutes + ":" + fSeconds;
    }
}
