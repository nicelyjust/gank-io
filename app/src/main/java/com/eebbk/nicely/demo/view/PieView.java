package com.eebbk.nicely.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.eebbk.nicely.demo.bean.PieVo;

import java.util.List;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   PieView
 *  @创建者:   lz
 *  @创建时间:  2017/10/28 14:50
 *  @修改时间:  Administrator 2017/10/28 14:50 
 *  @描述：
 */
public class PieView extends View {
    private static final String TAG = "PieView";
    private Paint mPaint;
    private List<PieVo> mPieVoList;
    private RectF mRectF;
    private int mW;
    private int mH;

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PieView(Context context) {
        this(context , null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public void setPieList(List<PieVo> pieVoList) {
        this.mPieVoList = pieVoList;
    }

    private void init() {
        mRectF = new RectF(-250 , -250 , 250 , 250);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画布移动坐标系
        canvas.translate(mW / 2 , mH / 2);
        float curAngle = 0 ;
        for (PieVo pieVo : mPieVoList) {
            mPaint.setColor(pieVo.getColor());
            canvas.drawArc(mRectF ,curAngle ,pieVo.getPercentage()*360 ,true , mPaint);
            curAngle += pieVo.getPercentage()*360;
        }
        canvas.translate(-mW / 2 , -mW / 2);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f , 0.9f);
            canvas.drawRect(mRectF ,mPaint);
        }
    }
}
