package com.eebbk.geek.live;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.eebbk.geek.R;


/*
 *  @项目名：  mio_sport
 *  @包名：    com.lifesense.mio.module.live
 *  @文件名:   LiveHRView
 *  @创建者:   lz
 *  @创建时间:  2019/11/26 15:36
 *  @描述：
 */
public class LiveHRView extends View {
    private static final String TAG = "LiveHRView";
    private final int DEFAULT_WIDTH = dp2px(210);
    private final int DEFAULT_HEIGHT = dp2px(210);
    private final Bitmap indicatorBp;
    private int width, height;
    private final int mStartAngle = 151, mSweepAngle = 240; // 起始角度 绘制角度
    private int mMin, mMax; // 最小值 最大值
    //内环环高
    private int innerRingWidth = dp2px(21);
    //内环padding
    private int innerRingPadding = dp2px(7);
    //内环的格数
    private int innerRingNumber = 95;
    //外环的高度
    private int outerRingHeight = dp2px(3), innerRingHeight = dp2px(3);
    //外环的格数
    private int outerRingNumber = 119;

    private float mCenterY;
    private float mCenterX;
    private static final String FIRST  = "#4FBFED";
    private static final String SECOND = "#05E16B";
    private static final String THIRD  = "#FE8C29";
    private static final String FOURTH = "#FF40E2";
    private static final String FIFTH  = "#EE291C";
    private final int[] zone3ColorResources = new int[]{
            0xFF4FBFED,
            0xFF05E16B,
            0xFFEE291C};
    private final int[] zone5ColorResources = new int[]{
            0xFF4FBFED,
            0xFF05E16B,
            0xFFFE8C29,
            0xFFFF40E2,
            0xFFEE291C};
    /**
     * 内环区间值
     */
    private int[] zones;

    /**
     * 内环区间值
     */
    private int[] zone3;
    /**
     * 颜色值
     */
    private int[] colors;
    /**
     * 实时的值,缺省-1代表第一次渲染
     */
    private int mCurValue = -1;
    /**
     * 背景画笔
     */
    private Paint mMBgPaint;
    private Paint mPointPaint;
    /**
     * 内环外环一个单位对应的radian
     */
    private float mInnerDegrees, mOutDegrees;

    private int[] mRecord = new int[2];
    private Paint mBitmapPaint;
    /**
     * 变换arrow位置和角度
     */
    private Matrix mMatrix;

    public LiveHRView(Context context) {
        this(context, null);
    }

    public LiveHRView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveHRView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        indicatorBp = BitmapFactory.decodeResource(getResources(), R.mipmap.arrow_2);
        mMatrix = new Matrix();
    }

    /**
     * @param zone3 外环区间值
     * @param zones 区间值,为null代表三区间
     */
    public void setZones(int[] zone3, @Nullable int[] zones) {
        this.zone3 = zone3;
        this.zones = zones == null ? zone3 : zones;
        this.colors = this.zones.length == 4 ? zone3ColorResources : zone5ColorResources;
        mMin = this.zones[0];
        mMax = this.zones[this.zones.length - 1];
    }

    public void setLiveValue(int value) {
        // 是否第一次渲染
        int valuePosition = getValuePosition(mMin, mMax, value, false);
        if (valuePosition > innerRingNumber) {
            valuePosition = innerRingNumber;
        }
        Log.d(TAG, "valuePosition: " + valuePosition);
        if (mCurValue == -1) {
            mRecord[0] = valuePosition;
            mRecord[1] = mRecord[0];
        } else if (valuePosition > mRecord[1]) {
            mRecord[1] = valuePosition;
        } else if (valuePosition < mRecord[0]){
            mRecord[0] = valuePosition;
        }
        this.mCurValue = value;
        invalidate();
    }

    private void initPaint() {
        mMBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMBgPaint.setColor(0xFF484848);
        mMBgPaint.setStyle(Paint.Style.FILL);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);
        mPointPaint.setStrokeWidth(dp2px(6));

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (measureWidthMode == MeasureSpec.AT_MOST
                && measureHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } else if (measureWidthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, measureHeightSize);
        } else if (measureHeightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(measureWidthSize, DEFAULT_HEIGHT);
        } else if (measureWidthMode == MeasureSpec.EXACTLY
                && measureHeightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(measureWidthSize, measureHeightSize);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        mCenterY = height / 2;
        mCenterX = width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mCenterX, mCenterY);
        canvas.rotate(mStartAngle - 180);

        drawBgRing(canvas);
        drawOutColorRing(canvas);

        canvas.save();
        float startAngle = mInnerDegrees * (mRecord[0] - 1);
        canvas.rotate(startAngle);
        int count = mRecord[1] - mRecord[0];

        int left = -(width >> 1) + outerRingHeight + innerRingPadding;
        int right = -(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth;
        for (int i = 0; i < count + 1; i++) {
            /*------------ 找出开始角度的颜色值 -------------*/
            int color = calculateBlockColor(zones, mRecord, i);
            Log.d(TAG, "current color: " + color);
            mMBgPaint.setColor(color);
            canvas.drawRect(left, innerRingHeight, right, 0, mMBgPaint);
            canvas.rotate(mInnerDegrees, 0, 0);
        }
        canvas.restore();

        drawArrow(canvas);

    }

    /**
     * 当前格子位于变色区间的位置,然后得到对应的颜色值
     *
     * @param zones  区间
     * @param record 记录开始结束位置
     * @param i      当前第几个
     * @return 颜色值
     */
    @ColorInt
    private int calculateBlockColor(int[] zones, int[] record, int i) {
        // etc. {19,38,57,76,95}--> {10,28,47,66,85}
        int[] blockPositions = calculateBlockPositions(zones);

        int length = blockPositions.length;
        int cur = record[0] + i;
        // 找到当前区间
        int sectionPos = getSectionPos(blockPositions, cur, length);
        Log.d(TAG, "mCurValue == " + mCurValue + ";sectionPos == " + sectionPos + "; cur == " + cur);
        if (length == 5) {
            if (sectionPos == 1) {
                return Color.parseColor(FIRST);
            } else if (sectionPos == 2) {
                float factor = i * 1.0f / (blockPositions[1] - blockPositions[0] +1);
                Log.d(TAG, "factor: " + factor);
                return i == 0 ? Color.parseColor(FIRST) : ColorGradient.calculateColor(FIRST, SECOND, factor);
            } else if (sectionPos == 3) {
                float factor = i * 1.0f / (blockPositions[2] - blockPositions[1] + 1);
                Log.d(TAG, "factor: " + factor);
                return i == 0 ? Color.parseColor(SECOND) : ColorGradient.calculateColor(SECOND, THIRD, factor);
            } else if (sectionPos == 4) {
                float factor = i * 1.0f / (blockPositions[3] - blockPositions[2] +1);
                Log.d(TAG, "factor: " + factor);
                return i == 0 ? Color.parseColor(THIRD) : ColorGradient.calculateColor(THIRD, FOURTH, factor);
            } else if (sectionPos == 5) {
                float factor = i * 1.0f / (blockPositions[4] - blockPositions[3]+1);
                Log.d(TAG, "factor: " + factor);
                return i == 0 ? Color.parseColor(FOURTH) : ColorGradient.calculateColor(FOURTH, FIFTH, factor);
            } else {
                return Color.parseColor(FIFTH);
            }
        }

        return Color.parseColor(FIRST);
    }

    /**
     * @param blockPositions 区间值数组
     * @param cur            第几格
     * @param length         区分三|五区间
     * @return 返回坐落的区间, 三区间1, 2, 3, 4;五区间,1,2,3,4,5,6
     */
    private int getSectionPos(int[] blockPositions, int cur, int length) {
        if (length == 3) {
            if (cur <= blockPositions[0]) {
                return 1;
            } else if (cur <= blockPositions[1]) {
                return 2;
            } else if (cur <= blockPositions[2]) {
                return 3;
            } else {
                return 4;
            }
        } else if (length == 5) {
            if (cur <= blockPositions[0]) {
                return 1;
            } else if (cur <= blockPositions[1]) {
                return 2;
            } else if (cur <= blockPositions[2]) {
                return 3;
            } else if (cur <= blockPositions[3]) {
                return 4;
            } else if (cur <= blockPositions[4]) {
                return 5;
            } else {
                return 6;
            }
        }
        return 1;
    }

    /**
     *
     * @param zones 三(四)区间,或者五(六)区间
     * @return 三区间,或者五区间不包括首尾
     */
    private int[] calculateBlockPositions(int[] zones) {
        int length = zones.length;
        int min = zones[0];
        int max = zones[length - 1];
        int[] blockPositions = new int[length-1];
        for (int i = 0; i < length-1; i++) {
            int position = getValuePosition(min, max, zones[i], false);
            int valuePosition = getValuePosition(min, max, zones[i + 1], false);
            int value = (valuePosition - position) / 2;
            blockPositions[i] = value + position;
        }
        return blockPositions;
    }

    private void drawArrow(Canvas canvas) {
        if (mCurValue == -1) {
            return;
        }
        canvas.save();
        int valuePosition = getValuePosition(mMin, mMax, mCurValue, false);
        float curAngle = mInnerDegrees * valuePosition + mInnerDegrees;
        canvas.rotate(curAngle);
        float fakeRad = (width >> 1) - outerRingHeight - innerRingPadding - innerRingWidth - (indicatorBp.getWidth() >> 1);
        mMatrix.reset();
        mMatrix.postRotate(30);
        mMatrix.postTranslate(-fakeRad, 0);

        mBitmapPaint.setColorFilter(new PorterDuffColorFilter(getArrowColor(zones, mCurValue), PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(indicatorBp, mMatrix, mBitmapPaint);
        canvas.restore();
    }

    /**
     * 画外环三区间值
     *
     * @param canvas canvas
     */
    private void drawOutColorRing(Canvas canvas) {
        if (zone3 == null) {
            return;
        }
        int valuePositionStart = getValuePosition(zone3[0], zone3[zone3.length - 1], zone3[1], true);
        int valuePositionEnd = getValuePosition(zone3[0], zone3[zone3.length - 1], zone3[2], true);
        canvas.save();
        float startAngle = mOutDegrees * (valuePositionStart - 1);
        canvas.rotate(startAngle);
        int count = valuePositionEnd - valuePositionStart;
        mMBgPaint.setColor(zone3ColorResources[1]);
        for (int i = 0; i < count; i++) {
            canvas.drawRect(-width / 2, outerRingHeight, -width / 2 + outerRingHeight, 0, mMBgPaint);
            canvas.rotate(mOutDegrees, 0, 0);
        }
        canvas.restore();
    }

    /**
     * 画背景圆环以及起始标识圆点
     *
     * @param canvas canvas
     */
    private void drawBgRing(Canvas canvas) {
        canvas.save();
        mMBgPaint.setColor(0xFF484848);
        // 外环一个单位对应的角度
        mOutDegrees = mSweepAngle * 1.0f / outerRingNumber;
        for (int i = 0; i < outerRingNumber; i++) {
            canvas.drawRect(-width / 2, outerRingHeight, -width / 2 + outerRingHeight, 0, mMBgPaint);
            canvas.rotate(mOutDegrees, 0, 0);
        }
        canvas.restore();
        // 内环一个单位对应的角度
        mInnerDegrees = mSweepAngle * 1.0f / innerRingNumber;
        canvas.save();
        mPointPaint.setColor(zone3ColorResources[0]);
        canvas.drawPoint(-(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth + mPointPaint.getStrokeWidth() + 5, 0, mPointPaint);
        for (int i = 0; i < innerRingNumber; i++) {
            canvas.drawRect(-(width >> 1) + outerRingHeight + innerRingPadding, innerRingHeight, -(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth, 0, mMBgPaint);
            canvas.rotate(mInnerDegrees, 0, 0);
        }
        mPointPaint.setColor(zone3ColorResources[2]);
        // width - 外环宽度- 内外环间距-内环宽度-画笔宽度-offset
        canvas.drawPoint(-(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth + mPointPaint.getStrokeWidth() + 5, mPointPaint.getStrokeWidth(), mPointPaint);
        canvas.restore();
    }

    /**
     * @param min   min value
     * @param max   max value
     * @param value 实时的value
     * @param outer 外环与否
     * @return 返回当前应该点亮的格子
     */
    private int getValuePosition(int min, int max, int value, boolean outer) {
        if (value == max) {
            return outer ? outerRingNumber : innerRingNumber;
        }
        int position = outer ? (value - min) * outerRingNumber / (max - min + 1) + 1 : (value - min) * innerRingNumber / (max - min + 1) + 1;
        return position <= 0 ? 1 : position;
    }

    @ColorInt
    private int getArrowColor(int[] zones, int curValue) {
        int color;
        int length = zones.length;
        if (length == 4) {
            //三区间
            if (curValue < zones[1]) {
                color = colors[0];
            } else if (curValue <= zones[2]) {
                color = colors[1];
            } else {
                color = colors[2];
            }
        } else {
            if (curValue < zones[1]) {
                color = colors[0];
            } else if (curValue < zones[2]) {
                color = colors[1];
            } else if (curValue < zones[3]) {
                color = colors[2];
            } else if (curValue < zones[4]) {
                color = colors[3];
            } else {
                color = colors[4];
            }
        }
        return color;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
}
