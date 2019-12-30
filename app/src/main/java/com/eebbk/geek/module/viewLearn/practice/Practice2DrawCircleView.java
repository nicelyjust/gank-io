package com.eebbk.geek.module.viewLearn.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.eebbk.geek.utils.TDevice;

public class Practice2DrawCircleView extends View {

    private Paint mPaint;
    private int mUnit;
    private Path mPath;
    private PorterDuffXfermode mXfermode;

    public Practice2DrawCircleView(Context context) {
        super(context);
        initPaint();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mUnit = w > h ? h : w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        mPaint.setXfermode(null);
        int cx = mUnit / 4;
        int radius = cx - 50;
        canvas.drawCircle(cx, cx, radius, mPaint);

        mPaint.setStrokeWidth(TDevice.dip2px(getContext(), 1));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx * 3, cx, radius, mPaint);

        mPaint.setStrokeWidth(TDevice.dip2px(getContext(), 1));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx * 3, cx, radius, mPaint);
        // path FillType
        mPath.reset();
        mPath.addCircle(cx, cx * 3, radius, Path.Direction.CW);
        mPath.addCircle(cx, cx * 3, radius - TDevice.dip2px(getContext(), 20), Path.Direction.CW);
        mPath.setFillType(Path.FillType.EVEN_ODD);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath, mPaint);

        canvas.drawCircle(cx * 3, cx * 3, radius, mPaint);

        mPaint.setXfermode(mXfermode);

        canvas.drawCircle(cx * 3, cx * 3, radius - TDevice.dip2px(getContext(), 20), mPaint);
        mPaint.setXfermode(null);
    }
}
