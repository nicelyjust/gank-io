package com.eebbk.geek.module.viewLearn.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.eebbk.geek.R;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.viewLearn.widget
 *  @文件名:   XferModeView
 *  @创建者:   lz
 *  @创建时间:  2019/12/20 13:38
 *  @描述：
 */
public class XferModeView extends View {
    private Paint mBitmapPaint;
    private PorterDuffXfermode mXfermode;
    private String mCurMode;
    private TextPaint mTextPaint;
    private Rect mTxtRect = new Rect();

    public XferModeView(Context context) {
        this(context, null);
    }

    public XferModeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XferModeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.XferModeView);
        int xferMode = typedArray.getInt(R.styleable.XferModeView_xferMode, 0);
        typedArray.recycle();
        String[] stringArray = getResources().getStringArray(R.array.xfer_mode);
        mCurMode = stringArray[xferMode];

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint.setDither(true);
        mBitmapPaint.setStrokeWidth(10);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.DKGRAY);
        mTextPaint.setTextSize(dip2px(18));

        mTextPaint.getTextBounds(mCurMode, 0, mCurMode.length(), mTxtRect);

        mXfermode = new PorterDuffXfermode(getMode(xferMode));
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(measureWidthSize, measureWidthSize+dip2px(15));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();

        mBitmapPaint.setStyle(Paint.Style.FILL);
        mBitmapPaint.setColor(0xFFFFCC44);
        canvas.drawARGB(255, 139, 197, 186);
        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        int radian = width / 3;
        canvas.drawCircle(radian, radian, radian, mBitmapPaint);
        mBitmapPaint.setXfermode(mXfermode);
        mBitmapPaint.setColor(Color.BLUE);
        mBitmapPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(radian, radian, radian * 2.7f, radian * 2.4f, mBitmapPaint);
        mBitmapPaint.setXfermode(null);
        canvas.restoreToCount(layer);

        canvas.drawText(mCurMode, width / 2 - mTxtRect.width() / 2, getHeight() -5 , mTextPaint);
    }

    private int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    private PorterDuff.Mode getMode(int xferMode) {
        PorterDuff.Mode mode;
        switch (xferMode) {
            case 0:
                mode = PorterDuff.Mode.CLEAR;
                break;
            case 1:
                mode = PorterDuff.Mode.SRC;
                break;
            case 2:
                mode = PorterDuff.Mode.DST;
                break;
            case 3:
                mode = PorterDuff.Mode.SRC_OVER;
                break;
            case 4:
                mode = PorterDuff.Mode.DST_OVER;
                break;
            case 5:
                mode = PorterDuff.Mode.SRC_IN;
                break;
            case 6:
                mode = PorterDuff.Mode.DST_IN;
                break;
            case 7:
                mode = PorterDuff.Mode.SRC_OUT;
                break;
            case 8:
                mode = PorterDuff.Mode.DST_OUT;
                break;
            case 9:
                mode = PorterDuff.Mode.SRC_ATOP;
                break;
            case 10:
                mode = PorterDuff.Mode.DST_ATOP;
                break;
            case 11:
                mode = PorterDuff.Mode.XOR;
                break;
            case 12:
                mode = PorterDuff.Mode.DARKEN;
                break;
            case 13:
                mode = PorterDuff.Mode.LIGHTEN;
                break;
            case 14:
                mode = PorterDuff.Mode.MULTIPLY;
                break;
            case 15:
            default:
                mode = PorterDuff.Mode.SCREEN;
                break;
        }
        return mode;
    }
}
