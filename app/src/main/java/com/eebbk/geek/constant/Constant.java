package com.eebbk.geek.constant;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.constant
 *  @文件名:   Constant
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 11:56
 *  @描述：    app常量类
 */

public class Constant {
    public static final int LOAD_TYPE_NORMAL = 0x1;
    // 上拉加载更多
    public static final int LOAD_TYPE_UP = 0x2;
    // 下拉刷新
    public static final int LOAD_TYPE_DOWN = 0x3;

    public static final int COUNT             = 10;
    public static final String DATA_EMPTY_MSG = "data is null";
    public static final long PULL_REFRESH_TIME = 60 * 60 * 1000;

    private Constant() {

    }
    public static class Extra{

        public static final String CATEGORY = "extra_category";
    }
    public static final class Category {
        public static final String DAY = "每日精选";
        public static final String IMAGE = "福利";
        public static final String Android = "Android";
        public static final String iOS = "iOS";
        public static final String FRONT_END = "前端";
        public static final String EXTRA_SOURCE = "拓展资源";
        public static final String VIDEOS = "休息视频";
    }
}
