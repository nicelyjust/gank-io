package com.eebbk.nicely.demo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.utils.DimentionUtils;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   LoadingView
 *  @创建者:   Administrator
 *  @创建时间:  2017/7/26 15:05
 *  @描述：    TODO
 */
public class LoadingView extends View {
    private static final String TAG = "LoadingView";
    private Paint mTxtPaint;
    private Paint mPaint;
    private Path mPath ;
    private String mDrawText;
    private int mColor ;
    private int mWidth = DimentionUtils.dip2px(getContext(), 100);
    private int mHeight = DimentionUtils.dip2px(getContext(), 100);
    private int mTextSize = DimentionUtils.sp2px(getContext(), 50);
    private Rect mRect;
    private float currentPercent;

    public LoadingView(Context context) {
        this(context , null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        mColor = typedArray.getColor(R.styleable.LoadingView_color , Color.rgb(41 , 163 , 254));
        mDrawText = typedArray.getString(R.styleable.LoadingView_text);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
        mPaint.setDither(true);
        //文字画笔（抗锯齿、白色、粗体）
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(Color.WHITE);
        mTxtPaint.setTypeface(Typeface.DEFAULT_BOLD);
        // 闭合波浪路径
        mPath = new Path();

        mRect = new Rect();

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPercent = animation.getAnimatedFraction();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTxtPaint.setColor(mColor);
        drawCenterTxt(canvas ,mTxtPaint, mDrawText);
        //上层的字
        mTxtPaint.setColor(Color.WHITE);
        //生成闭合波浪路径
        mPath = getActionPath(currentPercent);

        //裁剪成波浪形
        canvas.clipPath(mPath);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
        drawCenterTxt(canvas, mTxtPaint, mDrawText);
    }

    private Path getActionPath(float percent) {
        Path path = new Path();
        int x = -mWidth;
        //当前x点坐标（根据动画进度水平推移，一个动画周期推移的距离为一个mWidth）
        x += percent * mWidth;
        //波形的起点
        path.moveTo(x, mHeight / 2);
        //控制点的相对宽度
        int quadWidth = mWidth / 4;
        //控制点的相对高度
        int quadHeight = mHeight / 20 * 3;
        //第一个周期
        path.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0);
        path.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0);
        //第二个周期
        path.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0);
        path.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0);
        //右侧的直线
        path.lineTo(x + mWidth * 2, mHeight);
        //下边的直线
        path.lineTo(x, mHeight);
        //自动闭合补出左边的直线
        path.close();
        return path;
    }

    private void drawCenterTxt(Canvas canvas, Paint txtPaint, String drawText) {
        mRect.set(0, 0, mWidth, mHeight);
        txtPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = txtPaint.getFontMetrics();
        float             top         = fontMetrics.top;
        float             bottom      = fontMetrics.bottom;
        int centerY = (int) (mRect.centerY() - top / 2 - bottom / 2);
        canvas.drawText(drawText, mRect.centerX(), centerY, txtPaint);
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
}
