package com.eebbk.geek.draftpaper.tuya;
/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 13:16
 *  @修改时间:  nicely 2018/8/12 13:16
 *  @描述：    绘制请求封装类
 */

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawInvoker {
    // 绘制列表
    List<DrawPath> mDrawPaths = Collections.synchronizedList(new ArrayList<>());
    // 重做列表
    List<DrawPath> mRedos = Collections.synchronizedList(new ArrayList<>());

    public void add( DrawPath command){
        mDrawPaths.add(command);
    }
    public void execute(Canvas canvas){
        if (mDrawPaths.size() > 0){
            for (DrawPath drawPath : mDrawPaths) {
                drawPath.draw(canvas);
            }
        }
    }
    // 重做,把撤销的添加进去
    public void redo(){
        int size = mRedos.size();
        if (size > 0) {
            DrawPath drawPath = mRedos.get(size - 1);
            mRedos.remove(size - 1);
            mDrawPaths.add(drawPath);
        }
    }
    // 撤销
    public void undo(){
        int size = mDrawPaths.size();
        if (size > 0){
            DrawPath drawPath = mDrawPaths.get(size - 1);
            mDrawPaths.remove(size - 1);
            mRedos.add(drawPath);
        }
    }

    /**
     * 是否能撤销
     * @return
     */
    public boolean isCanUndo(){
        return mDrawPaths.size() >  0;
    }

    /**
     * 是否能重做
     * @return
     */
    public boolean isCanRedo(){
        return mRedos.size() >  0;
    }
}
