package com.eebbk.geek.bean.netBean;

/*
 *  @项目名：  Demo
 *  @包名：    com.eebbk.nicely.demo.bean.netBean
 *  @文件名:   DayInfoVo
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 10:29
 *  @描述：response跟JSON有区别时用 @SerializedName(response的本来名字)
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayInfoVo {
    /**
     * {"Android":[{"_id":"5a085efb421aa90fe50c01f6","createdAt":"2017-11-12T22:47:23.292Z","desc":"Android持续集成：Jenkins+Github+蒲公英/Fir.im+邮件通知","publishedAt":"2017-11-13T12:10:58.643Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488151&idx=1&sn=f329687505b416cd898c843ca558b693","used":true,"who":"陈宇明"},{"_id":"5a08f676421aa90fe50c01f7","createdAt":"2017-11-13T09:33:42.968Z","desc":"Android性能优化之列表卡顿（以\u201c简书\u201d为例）","publishedAt":"2017-11-13T12:10:58.643Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488175&idx=1&sn=b8d044fdd6b534b685e84a8fdf25398b","used":true,"who":"陈宇明"},{"_id":"5a091a1d421aa90fe50c01f8","createdAt":"2017-11-13T12:05:49.452Z","desc":"兼容 RFC 3986 的 URL lib。","publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"Android","url":"https://github.com/EricEdens/urllib","used":true,"who":"代码家"},{"_id":"5a091a8a421aa90fef20351e","createdAt":"2017-11-13T12:07:38.313Z","desc":"Android Bottom Sheet 布局效果。","images":["http://img.gank.io/3fcb8ca3-fb8c-4958-9dd7-e986e3743407"],"publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"Android","url":"https://github.com/qhutch/BottomSheetLayout","used":true,"who":"代码家"}],
     * "iOS":[{"_id":"5a091a6c421aa90fe7253629","createdAt":"2017-11-13T12:07:08.438Z","desc":"类似 iOS 本身的 Pull Up 控制器效果。","images":["http://img.gank.io/e28303d0-e4af-461a-b1e2-2405f81658db"],"publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"iOS","url":"https://github.com/MarioIannotta/PullUpController","used":true,"who":"代码家"},{"_id":"5a091abd421aa90fe50c01fa","createdAt":"2017-11-13T12:08:29.438Z","desc":"可以制作 emoji 人脸动画的 App，跟官方的效果一样","images":["http://img.gank.io/dc574021-8029-4e4f-afe7-c26fde301e84"],"publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"iOS","url":"https://github.com/insidegui/AnimojiStudio","used":true,"who":"代码家"}],
     * "休息视频":[{"_id":"5a06d733421aa90fef20351b","createdAt":"2017-11-11T18:55:47.563Z","desc":"\u201c叫阿姨\u201d进阶版来了！当女生被叫\u201c奶奶\u201d，还会那么淡定吗？","publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av16137432/","used":true,"who":"lxxself"}],
     * "福利":[{"_id":"5a08ea7b421aa90fe7253628","createdAt":"2017-11-13T08:42:35.306Z","desc":"11-13","publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg","used":true,"who":"daimajia"}]}
     */
    private List<DataInfo> iOS;
    private List<DataInfo> Android;
    //瞎推荐
    @SerializedName("瞎推荐")
    private List<DataInfo> blind;
    //拓展资源
    @SerializedName("拓展资源")
    private List<DataInfo> resource;
    @SerializedName("福利")
    private List<DataInfo> welfare;
    @SerializedName("休息视频")
    private List<DataInfo> videos;

    public List<DataInfo> getiOS() {
        return iOS;
    }

    public void setiOS(List<DataInfo> iOS) {
        this.iOS = iOS;
    }

    public List<DataInfo> getAndroid() {
        return Android;
    }

    public void setAndroid(List<DataInfo> android) {
        Android = android;
    }
    public List<DataInfo> getBlind() {
        return blind;
    }

    public void setBlind(List<DataInfo> blind) {
        this.blind = blind;
    }

    public List<DataInfo> getResource() {
        return resource;
    }

    public void setResource(List<DataInfo> resource) {
        this.resource = resource;
    }

    public List<DataInfo> getWelfare() {
        return welfare;
    }

    public void setWelfare(List<DataInfo> welfare) {
        this.welfare = welfare;
    }

    public List<DataInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<DataInfo> videos) {
        this.videos = videos;
    }
}
