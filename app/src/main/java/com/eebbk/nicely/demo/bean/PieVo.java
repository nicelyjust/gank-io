package com.eebbk.nicely.demo.bean;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.bean
 *  @文件名:   PieVo
 *  @创建者:   lz
 *  @创建时间:  2017/10/28 14:57
 *  @修改时间:  Administrator 2017/10/28 14:57 
 *  @描述：    TODO
 */
public class PieVo {
    private static final String TAG = "PieVo";
    private float  percentage;
    private float  value;
    private String tagName ;

    private int color;

    public PieVo(float value, float percentage, String tagName) {
        this.value = value;
        this.percentage = percentage;
        this.tagName = tagName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getPercentage() {
        return percentage;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPercentage(float percentage) {

        this.percentage = percentage;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
