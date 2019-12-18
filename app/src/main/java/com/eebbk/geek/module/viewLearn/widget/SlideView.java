package com.eebbk.geek.module.viewLearn.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import androidx.annotation.Nullable;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.viewLearn.widget
 *  @文件名:   SlideView
 *  @创建者:   lz
 *  @创建时间:  2019/12/18 19:39
 *  @描述：    自定义View滑动
 */
public class SlideView extends View {

    private int mScaledTouchSlop;
    private Paint mPaint;
    private int mW;
    private int mH;
    private Scroller mScroller;

    public SlideView(Context context) {
        this(context,null);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mW/2,mH/2,200,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
            default:

                break;
        }
        return true;
    }
}
