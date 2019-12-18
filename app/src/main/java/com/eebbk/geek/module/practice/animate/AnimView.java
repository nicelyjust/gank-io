package com.eebbk.geek.module.practice.animate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.eebbk.geek.utils.TDevice;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.practice.animate
 *  @文件名:   AnimView
 *  @创建者:   lz
 *  @创建时间:  2019/10/12 10:39
 *  @描述：
 */
public class AnimView extends View {

    private Paint mPaint;
    private int mRadius;
    private Point mCurPoint;
    private TimeInterpolator interpolatorType = new LinearInterpolator();
    /**
     * 实现关于color 的属性动画
     */
    private String color = "#0000FF";
    private AnimatorSet mAnimatorSet;

    public AnimView(Context context) {
        this(context,null);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mRadius = TDevice.dip2px(context, 20);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurPoint != null) {
            mPaint.setColor(Color.parseColor(color));
            canvas.drawCircle(mCurPoint.getX(),mCurPoint.getY(),mRadius,mPaint);
        }
    }

    public void startAnim() {
        Point mStartPoint = new Point(mRadius, mRadius);
        Point mEndPoint = new Point(getWidth() - mRadius, getHeight() - mRadius);

        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointLineEvaluator(), mStartPoint, mEndPoint);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(animation -> {
            mCurPoint = (Point) animation.getAnimatedValue();
            invalidate();
        });
        mAnimatorSet = new AnimatorSet();
        ObjectAnimator color = ObjectAnimator.ofObject(this, "color",new ColorEvaluator(),"#0000FF", "#FF0000");
        color.setRepeatMode(ValueAnimator.REVERSE);
        color.setDuration(5000);
        color.setRepeatCount(-1);

        mAnimatorSet.play(valueAnimator)
                .with(color);
        mAnimatorSet.setDuration(5000);
        mAnimatorSet.setInterpolator(interpolatorType);
        mAnimatorSet.start();
    }

    public void pauseAnimation() {
        if (mAnimatorSet != null) {
            mAnimatorSet.pause();
        }
    }


    public void stopAnimation() {
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
            this.clearAnimation();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }
}
