package com.eebbk.nicely.demo.http.api;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.http.api
 *  @文件名:   ServerApi
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/13 20:50
 *  @描述：    GankIO Api 接口 实际使用 getDayGank 和 getData 两个接口
 */

import com.eebbk.nicely.demo.bean.DataInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {
    boolean DEBUG   = true;
    /**
     * 获取分类数据
     *
     * @param type  数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param count 请求个数： 数字，大于0
     * @param page  第几页：数字，大于0
     * @return
     */
    @GET("data/{type}/{count}/{page}")
    Observable<GankInfo<List<DataInfo>>> getData(@Path("type") String type,
                                                 @Path("count") int count, @Path("page") int page);
}
