package com.eebbk.geek.bean.netBean;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.bean.netBean
 *  @文件名:   DataInfoVo
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 10:10
 *  @描述：
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataInfoVo {
    /**
     * _id : 5a08ea7b421aa90fe7253628
     * createdAt : 2017-11-13T08:42:35.306Z
     * desc : 11-13
     * publishedAt : 2017-11-13T12:10:58.643Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg
     * used : true
     * who : daimajia
     */
    @SerializedName("_id")
    private String id;

    @SerializedName("createdAt")
    private String  createdTime;

    private String  desc;
    @SerializedName("publishedAt")
    private String  publishedTime;

    private List<String> images;

    private String  source;

    private String  type;

    private String  url;

    private boolean used;

    private String  who;

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "DataInfoVo{" + "id='" + id + '\'' + ", createdTime='" + createdTime + '\'' + ", desc='" + desc + '\'' + ", publishedTime='" + publishedTime + '\'' + ", source='" + source + '\'' + ", type='" + type + '\'' + ", url='" + url + '\'' + ", used=" + used + ", who='" + who + '\'' + '}';
    }
}
