package com.eebbk.geek.bean;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.bean
 *  @文件名:   CityBean
 *  @创建者:   lz
 *  @创建时间:  2017/10/25 19:35
 *  @修改时间:  Administrator 2017/10/25 19:35 
 *  @描述：    TODO
 */
public class CityBean {
    private static final String TAG = "CityBean";
    private String tag;
    private String name;

    public CityBean(String tag, String name) {
        this.tag = tag;
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
