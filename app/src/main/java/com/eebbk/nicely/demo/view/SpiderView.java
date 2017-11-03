package com.eebbk.nicely.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.centerX;
import static android.R.attr.centerY;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   SpiderView
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/3 17:27
 *  @描述：    TODO
 */

public class SpiderView extends View {

    private int mCenterX;
    private int mCenterY;
    private Context mContext;

    private int count = 6;                //数据个数
    private float angle = (float) (Math.PI*2/count);
    private float radius;                   //网格最大半径
    private String[] titles = {"a","b","c","d","e","f"};
    private double[] data = {100,60,60,60,100,50,10,20}; //各维度分值
    private float maxValue = 100;             //数据最大值
    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint textPaint;                //文本画笔
    private Path  mLPath;

    public SpiderView(Context context) {
        this(context , null);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public SpiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }
     private void init() {
         mLPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w/2 ;
        mCenterY = h/2;
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 求出最小的边长
        float r = radius / (count - 1);
        for (int i = 1; i <= count; i++) {
            float curR = r*i;
            mLPath.reset();
            mLPath.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    mLPath.moveTo(mCenterX + curR, mCenterY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (mCenterX + curR * Math.cos(angle * j));
                    float y = (float) (mCenterY + curR * Math.sin(angle * j));
                    mLPath.lineTo(x, y);
                }
            }
            mLPath.close();//闭合路径
            canvas.drawPath(mLPath, mainPaint);

        }
    }
}
