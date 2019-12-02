package com.eebbk.nicely.geek;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.geek
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 9:52
 *  @修改时间:  nicely 2018/8/12 9:52
 *  @描述：
 */


import org.junit.Test;

import java.util.Arrays;

public class SampleTest {
    /**
     * 一般代码: 直接new Receiver(),toLeft,toRight...
     * GUI 开发中比较常用
     *
     * @throws Exception
     */
    @Test
    public void addition_isCorrect()
            throws Exception {
        int[] ints = calculateBlockPositions(new int[]{98, 117, 137, 156, 176, 195});
        System.out.println(Arrays.toString(ints));
    }
    /**
     *
     * @param zones 三(四)区间,或者五(六)区间
     * @return 三区间,或者五区间不包括首尾
     */
    private int[] calculateBlockPositions(int[] zones) {
        int length = zones.length;
        int min = zones[0];
        int max = zones[length - 1];
        int[] blockPositions = new int[length-1];
        for (int i = 0; i < length-1; i++) {
            int position = getValuePosition(min, max, zones[i], false);
            int valuePosition = getValuePosition(min, max, zones[i + 1], false);
            int value = (valuePosition - position) / 2;
            blockPositions[i] = value + position;
        }
        return blockPositions;
    }

    /**
     * @param min   min value
     * @param max   max value
     * @param value 实时的value
     * @param outer 外环与否
     * @return 返回当前应该点亮的格子
     */
    private int getValuePosition(int min, int max, int value, boolean outer) {
        if (value == max) {
            return outer ? 119 : 95;
        }
        int position = outer ? (value - min) * 119 / (max - min + 1) + 1 : (value - min) * 95 / (max - min + 1) + 1;
        return position <= 0 ? 1 : position;
    }
}
