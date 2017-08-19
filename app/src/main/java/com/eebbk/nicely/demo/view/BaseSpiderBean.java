package com.eebbk.nicely.demo.view;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   BaseSpiderBean
 *  @创建者:   lz
 *  @创建时间:  2017/8/19 17:40
 *  @修改时间:  Administrator 2017/8/19 17:40 
 *  @描述：    TODO
 */
public abstract class BaseSpiderBean {
    public abstract String  getSpiderName();
    public abstract boolean  getIsSelectState ();
    public abstract void   setSelectState (boolean b);
}
