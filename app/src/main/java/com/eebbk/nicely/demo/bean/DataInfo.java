package com.eebbk.nicely.demo.bean;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.bean
 *  @文件名:   DataInfo
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/13 21:05
 *  @描述：    TODO
 */

import java.util.List;

public class DataInfo {

    /**
     * error : false
     * results : [{
     "_id": "5a08ea7b421aa90fe7253628",
     "createdAt": "2017-11-13T08:42:35.306Z",
     "desc": "11-13",
     "publishedAt": "2017-11-13T12:10:58.643Z",
     "source": "chrome",
     "type": "\u798f\u5229",
     "url": "http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg",
     "used": true,
     "who": "daimajia"
     },..]
     */

    private boolean error;
    private List<DataInfoVo> results;

    public boolean isError() { return error;}

    public void setError(boolean error) { this.error = error;}

    public List<DataInfoVo> getResults() { return results;}

    public void setResults(List<DataInfoVo> results) { this.results = results;}

    public static class DataInfoVo {
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

        private String _id;
        private String  createdAt;
        private String  desc;
        private String  publishedAt;
        private String  source;
        private String  type;
        private String  url;
        private boolean used;
        private String  who;

        public String get_id() { return _id;}

        public void set_id(String _id) { this._id = _id;}

        public String getCreatedAt() { return createdAt;}

        public void setCreatedAt(String createdAt) { this.createdAt = createdAt;}

        public String getDesc() { return desc;}

        public void setDesc(String desc) { this.desc = desc;}

        public String getPublishedAt() { return publishedAt;}

        public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt;}

        public String getSource() { return source;}

        public void setSource(String source) { this.source = source;}

        public String getType() { return type;}

        public void setType(String type) { this.type = type;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public boolean isUsed() { return used;}

        public void setUsed(boolean used) { this.used = used;}

        public String getWho() { return who;}

        public void setWho(String who) { this.who = who;}
    }
}
