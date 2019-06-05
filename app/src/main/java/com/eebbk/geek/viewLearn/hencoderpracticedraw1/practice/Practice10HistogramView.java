package com.eebbk.geek.viewLearn.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.eebbk.geek.utils.TDevice;

import java.util.Random;

public class Practice10HistogramView extends View {

    private int mW;
    private int mH;
    private Paint mLinePaint;
    private Path mPath;
    private Paint mPaint;
    private Random mRandom;

    public Practice10HistogramView(Context context) {
        super(context);
        initPaint();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;
    }

    private void initPaint() {
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(TDevice.dip2px(getContext(), 1));

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mRandom = new Random();

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        int gapStart = TDevice.dip2px(getContext(), 20);
        int gapEnd = TDevice.dip2px(getContext(), 20);
        int gapTop = TDevice.dip2px(getContext(), 20);
        int gapBottom = TDevice.dip2px(getContext(), 100);

        int width = mW - gapStart - gapEnd;
        int chartHeight = mW - gapTop - gapBottom;
        mPath.reset();
        mPath.moveTo(gapStart - TDevice.dip2px(getContext(), 1), gapTop);
        mPath.lineTo(gapStart - TDevice.dip2px(getContext(), 1), chartHeight);
        mPath.lineTo(gapStart + width, chartHeight);

        canvas.drawPath(mPath, mLinePaint);

        int itemWidth = (width - TDevice.dip2px(getContext(), 5)) / 7 - TDevice.dip2px(getContext(), 4);

        canvas.save();
        canvas.translate(gapStart, chartHeight);

        for (int i = 0; i < 7; i++) {
            canvas.drawRect(TDevice.dip2px(getContext(), 4), -mRandom.nextInt(chartHeight - gapTop), itemWidth, 0, mPaint);
            canvas.translate(TDevice.dip2px(getContext(), 4) + itemWidth, 0);
        }
        canvas.restore();

    }
}
