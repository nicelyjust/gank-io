package com.eebbk.nicely.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.eebbk.nicely.demo.utils.TDevice;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   PaintView
 *  @创建者:   lz
 *  @创建时间:  2017/7/24 14:54
 *  @描述：    自定义view需要重写测量
 */
public class PaintView extends View {
    private static final String TAG = "PaintView";
    private Paint mPaint;
    private Paint mTxtPaint;
    private Path mPath;
    private String mDrawText= "辅";
    private int mTextSize = TDevice.dip2px(getContext(), 50);
    private int mHeight = TDevice.dip2px(getContext(), 100);
    private int mWidth = TDevice.dip2px(getContext(), 100);
    private Rect mRect;
    private int mColor;

    public PaintView(Context context) {
        this(context , null);
    }

    public PaintView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 画圆的笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
        mPaint.setDither(true);

        //文字画笔（抗锯齿粗体）
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColor = Color.rgb(41, 163, 254);
        mTxtPaint.setColor(mColor);
        mTxtPaint.setTypeface(Typeface.DEFAULT_BOLD);

        mRect = new Rect();
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize      = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize       = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth , mHeight);
        mTextSize = mWidth/2;
        mTxtPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画底层的字
        mTxtPaint.setColor(mColor);
        drawCentertxt(canvas , mTxtPaint , mDrawText);
        mPath = getActionPath();
        canvas.clipPath(mPath);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
    }

    private void drawCentertxt(Canvas canvas, Paint txtPaint, String drawText) {
        mRect.set(0, 0, mWidth, mHeight);
        txtPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        float             top         = fontMetrics.top;
        float             bottom      = fontMetrics.bottom;
        int centerY = (int) (mRect.centerY() - top / 2 - bottom / 2);
        canvas.drawText(drawText , mRect.centerX() , centerY , txtPaint);
    }
    private Path getActionPath() {
        mPath.moveTo(0 , mHeight / 2);
        mPath.lineTo(mWidth / 2, mHeight / 2);
        return mPath;
    }
}
