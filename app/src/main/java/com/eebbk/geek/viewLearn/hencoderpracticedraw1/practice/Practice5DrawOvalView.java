package com.eebbk.geek.viewLearn.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.eebbk.geek.utils.TDevice;

public class Practice5DrawOvalView extends View {

    private int mW;
    private int mH;
    private Paint mPaint;

    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mW = w;
        mH = h;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(TDevice.dip2px(getContext(), 20));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawOval() 方法画椭圆
        int save = canvas.save();
        canvas.translate(mW / 2, mH / 2);
        canvas.drawOval(-200, -100, 200, 100, mPaint);
        canvas.restoreToCount(save);

        canvas.drawRoundRect(100,100,300,300,TDevice.dip2px(getContext(),10),TDevice.dip2px(getContext(),10),mPaint);

    }
}
