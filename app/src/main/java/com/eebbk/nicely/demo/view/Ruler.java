package com.eebbk.nicely.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.eebbk.nicely.demo.utils.TDevice;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.view
 *  @文件名:   Ruler
 *  @创建者:   lz
 *  @创建时间:  2017/10/30 12:05
 *  @修改时间:  Administrator 2017/10/30 12:05 
 *  @描述：先定位中间的value 换算成刻度,然后绘制前后的刻度
 */
public class Ruler extends View{
    private static final String TAG = "Ruler";
    private Paint mLongLinePaint;
    private Paint mShortLinePaint;
    private int gapWidth = 8;// dp
    private int longLineHeight = 40;// dp
    private int shortLineHeight = 25;// dp
    private int textSize = 15;// sp
    /**
     * 这个是指示器距离左边那个刻度的距离（0,tapeWidth）
     * 绘制的时候要注意这个数值
     */
    private int mGapOffset;
    /*------------ 放大10倍 -------------*/
    private int minValue = 0;// 440 -->44.0kg
    private int maxValue = 1000;// 1000 -->100kg
    private int defaultValue = 500;// 1000 -->100kg
    private int mCurValue;// 1000 -->100kg

    private int mWidthSize;
    private Context mContext;
    private Paint indicatorPaint;
    private Rect textRect;
    private TextPaint textPaint;
    private int numberHeight;
    private Scroller mScroller;
    private float lastX;
    private float moveX;

    public Ruler(Context context) {
        this(context , null);
    }

    public Ruler(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public Ruler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        gapWidth = TDevice.dip2px(mContext , gapWidth);
        longLineHeight = TDevice.dip2px(mContext , longLineHeight);
        shortLineHeight = TDevice.dip2px(mContext , shortLineHeight);
        textSize = TDevice.dip2px(mContext , textSize);
        mCurValue = defaultValue;
        init();
    }

    private void init() {
        initPaint();
        textRect = new Rect();
        //直接获取一下数字高度
        textPaint.getTextBounds("1", 0, 1, textRect);
        numberHeight = textRect.height();
        mScroller = new Scroller(mContext);
    }

    private void initPaint() {
        textPaint = new TextPaint();
        textPaint.setTextSize(48);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#555855"));

        mLongLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLongLinePaint.setColor(Color.WHITE);
        mLongLinePaint.setStyle(Paint.Style.FILL);
        mLongLinePaint.setStrokeWidth(TDevice.dip2px(mContext , 3));

        mShortLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShortLinePaint.setColor(Color.WHITE);
        mShortLinePaint.setStyle(Paint.Style.FILL);
        mShortLinePaint.setStrokeWidth(TDevice.dip2px(mContext , 2));

        indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indicatorPaint.setColor(Color.parseColor("#6DB67C"));
        indicatorPaint.setAntiAlias(true);
        indicatorPaint.setStrokeCap(Paint.Cap.ROUND);
        indicatorPaint.setStrokeWidth(TDevice.dip2px(mContext , 5));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        mWidthSize = (int)TDevice.getScreenWidthPX() - 200;
        if (widthMode == MeasureSpec.EXACTLY) {
            int size =  MeasureSpec.getSize(widthMeasureSpec);
            if (size >= mWidthSize) {
                mWidthSize = size;
            }
        }
        setMeasuredDimension(mWidthSize, TDevice.dip2px(mContext ,100));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        1.先画背景
        canvas.drawColor(Color.parseColor("#FBE40C"));
        drawLeft(canvas);
        drawRight(canvas);
        drawCursor(canvas);
        //
    }

    private void drawLeft(Canvas canvas) {
        int drawLength = 0;
        int curValue = mCurValue;
        int totalWidth = mWidthSize / 2;
        while (curValue >= 0) {
            if (curValue % 10 == 0) {
                // 长线 + 文字
                canvas.drawLine(totalWidth - drawLength , 0 , totalWidth - drawLength , longLineHeight ,mLongLinePaint );
                drawText(canvas , curValue , totalWidth - drawLength);
            } else {
                canvas.drawLine(totalWidth - drawLength , 0 , totalWidth - drawLength , shortLineHeight ,mShortLinePaint );
            }
            curValue --;
            drawLength += gapWidth;
        }

    }

    private void drawText(Canvas canvas, int value, int x) {
        String drawStr = value/10 + "";
        textPaint.getTextBounds(drawStr, 0, drawStr.length(), textRect);
        float width = textPaint.measureText(drawStr);
        canvas.drawText(drawStr, x - width / 2, longLineHeight + numberHeight + shortLineHeight, textPaint);
    }

    private void drawRight(Canvas canvas) {
        int drawLength = 0;
        int curValue = mCurValue + 1;
        int totalWidth = mWidthSize / 2;
        // TODO: 2017/10/31 看不见的就不应该绘制
        while (curValue <= maxValue) {
            if (curValue % 10 == 0) {
                // 长线 + 文字
                canvas.drawLine(totalWidth + drawLength , 0 , totalWidth + drawLength , longLineHeight ,mLongLinePaint );
                drawText(canvas , curValue , totalWidth + drawLength);
            } else {
                canvas.drawLine(totalWidth + drawLength , 0 , totalWidth + drawLength , shortLineHeight ,mShortLinePaint );
            }
            curValue ++;
            drawLength += gapWidth;
        }
    }

    private void drawCursor(Canvas canvas) {

        canvas.drawLine(mWidthSize/2 , 0 , mWidthSize/2 , longLineHeight , indicatorPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getX() - lastX;
                moveX += dx;
                // 1.计算偏移量并换算成刻度
                // 2.得到当前的value,触发绘制
                int valueOffset = (int) (Math.abs(moveX) / gapWidth);
                float gapOffset = Math.abs(moveX) % gapWidth;
                if (gapOffset >= mGapOffset/2) {
                    valueOffset = valueOffset +1;
                }
                if (dx > 0) {
                    mCurValue = mCurValue + valueOffset;
                } else {
                    mCurValue = mCurValue - valueOffset;
                }

                scrollBy(-(int) dx ,0);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                invalidate();
                break;
            default:
                 break;
        }
        lastX = event.getX();
        return true;
    }
}
