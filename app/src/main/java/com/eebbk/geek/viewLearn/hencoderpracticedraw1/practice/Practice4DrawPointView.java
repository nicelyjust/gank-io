package com.eebbk.geek.viewLearn.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.eebbk.geek.utils.TDevice;

public class Practice4DrawPointView extends View {

    private Paint mPaint;
    private int mW;
    private int mH;

    public Practice4DrawPointView(Context context) {
        super(context);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(TDevice.dip2px(getContext(), 20));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPoint() 方法画点
//        一个圆点，一个方点
//        圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(mW/4, mH / 2, mPaint);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(mW/2, mH / 2, mPaint);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(mW/4*3, mH / 2, mPaint);
    }
}
