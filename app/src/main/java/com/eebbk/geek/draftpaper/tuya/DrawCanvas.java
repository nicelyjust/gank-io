package com.eebbk.geek.draftpaper.tuya;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.draftpaper.tuya
 *  @创建者:   lz
 *  @创建时间:  2018/8/12 12:48
 *  @修改时间:  nicely 2018/8/12 12:48
 *  @描述：    涂鸦控件;具体命令接收者
 */

public class DrawCanvas extends SurfaceView {

    public DrawCanvas(Context context) {
        this(context , null);
    }

    public DrawCanvas(Context context, AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public DrawCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }
}
