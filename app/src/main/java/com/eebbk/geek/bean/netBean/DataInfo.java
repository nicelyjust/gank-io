package com.eebbk.geek.bean.netBean;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.bean
 *  @文件名:   DataInfo
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/13 21:05
 *  @描述：    根据查看返回信息,可以将本属于两个接口的api合并成一个,声明泛型即可
 */

import java.util.List;

public class DataInfo<T> {

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

    private boolean      error;
    private T            results;
    private List<String> category;

    public boolean isError() { return error;}

    public void setError(boolean error) { this.error = error;}

    public T getResults() { return results;}

    public void setResults(T results) { this.results = results;}

}
