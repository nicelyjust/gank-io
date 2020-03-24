package com.eebbk.geek.module.viewLearn.live;

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
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
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
    private final int DEFAULT_WIDTH = dp2px(271);
    private final int DEFAULT_HEIGHT = dp2px(271);
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
    // 圆点的半径
    private int mPointRadian = dp2px(4);
    private float mCenterY;
    private float mCenterX;
    private static final String FIRST  = "#FF4FBFED";
    private static final String SECOND = "#FF05E16B";
    private static final String THIRD  = "#FFFE8C29";
    private static final String FOURTH = "#FFFF40E2";
    private static final String FIFTH  = "#FFEE291C";
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
    private float mInnerDegrees;
    /**
     * index 0 代表inner ring绘制的下区间,index 1 代表上区间,index 2为控制是否绘制inner ring
     */
    private int[] mRecord = new int[3];
    private Paint mBitmapPaint;
    /**
     * 变换arrow位置和角度
     */
    private Matrix mMatrix;
    private Paint mTextPaint;
    /**
     * 测量文字用
     */
    private Rect mRectF;
    /**
     * 三区间的区间值文字画笔
     */
    private TextPaint mTargetPaint;

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
        mRectF = new Rect();
    }

    /**
     *  三区间初始化
     * @param zone3 三区间范围
     */
    public void setZone3(@NonNull int[] zone3) {
        this.zone3 = zone3;
        this.colors = this.zone3.length == 4 ? zone3ColorResources : zone5ColorResources;

        int below = zone3[1];
        int above = zone3[2];
        int centerZoneCount = above - below + 1;

        if (centerZoneCount <= 45) {
            //一个心率值占两格
            int frontCount = (innerRingNumber - (centerZoneCount * 2)) / 2;
            int endCount = frontCount + 1;
            mMin = below - (frontCount / 2);
            mMax = above + (endCount / 2);

        } else if (centerZoneCount <= innerRingNumber) {
            //一个心率值占一格,
            int frontCount = (innerRingNumber - centerZoneCount) / 2;
            int endCount;
            if (centerZoneCount % 2 == 1) {
                //单数,左右侧一样多.
                endCount = frontCount;
            } else {
                //双数,右侧比左侧多一个.
                endCount = frontCount + 1;
            }
            mMin = below - frontCount;
            mMax = above + endCount;
        } else {
            //大于95
            int frontCount = (centerZoneCount - innerRingNumber) / 2;
            int endCount;
            if (centerZoneCount % 2 == 1) {
                //单数
                endCount = frontCount;
            } else {
                //双数
                endCount = frontCount + 1;
            }
            mMin = below + frontCount;
            mMax = above - endCount;
        }
        Log.d(TAG, "setZone3: mMin == " + mMin + ":mMax == " +mMax);
    }

    /**
     *  五区间初始化
     * @param zone5 五区间值
     */
    public void setZone5(@NonNull int[] zone5) {
        this.zones = zone5;
        this.colors = this.zones.length == 4 ? zone3ColorResources : zone5ColorResources;
        mMin = this.zones[0];
        mMax = this.zones[this.zones.length - 1];
    }

    public void setLiveValue(int value) {
        // 是否第一次渲染
        int valuePosition = getValuePosition(mMin, mMax, value,zone3 != null);
        if (valuePosition > innerRingNumber) {
            valuePosition = innerRingNumber;
        }
        if (value >= mMin) {
            mRecord[2] = 1;
        }
        if (mCurValue == -1) {
            mRecord[0] = valuePosition;
            mRecord[1] = mRecord[0];
        } else if (valuePosition > mRecord[1]) {
            mRecord[1] = valuePosition;
        } else if (valuePosition < mRecord[0]) {
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

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint.setDither(true);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(dp2px(80));
        mTextPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro-Medium.otf"));

        mTargetPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTargetPaint.setTextSize(dp2px(18));
        mTargetPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro-Medium.otf"));
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
        width = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mCenterX, mCenterY);
        int degrees = mStartAngle - 180;
        canvas.rotate(degrees);


        if (zones != null){
            drawBgRing(canvas);
            drawInnerColorRing(canvas);
        }
        else{
            drawBgRing3(canvas);
            drawInnerColorRing3(canvas);
        }
        drawText(canvas,degrees);
        drawArrow(canvas);

    }

    private void drawText(Canvas canvas, int degrees) {
        int save = canvas.save();
        canvas.rotate(-degrees);
        String value;
        if (mCurValue == -1){
            value = "--";
            mTextPaint.setColor(0xFFFFFFFF);
        } else {
            value = String.valueOf(mCurValue);
            mTextPaint.setColor(mCurValue < mMin ? 0xFFFFFFFF :getCurrentValueColor(zones == null?zone3:zones, mCurValue));
        }
        mTextPaint.getTextBounds(value,0,value.length(),mRectF);
        canvas.drawText(value, -mRectF.width()/2, mRectF.height()*0.25f, mTextPaint);
        canvas.restoreToCount(save);

    }

    private void drawInnerColorRing(Canvas canvas) {
        if (mCurValue == -1 || mRecord[2] == 0) {
            return;
        }
        canvas.save();
        float startAngle = mInnerDegrees * (mRecord[0] - 1);
        canvas.rotate(startAngle);
        int count = mRecord[1] - mRecord[0] + 1;
        Log.d(TAG, "onDraw: count == " + count);
        int left = -(width >> 1) + outerRingHeight + innerRingPadding;
        int right = -(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth;
        for (int i = 0; i < count; i++) {
            /*------------ 找出开始角度的颜色值 -------------*/
            int color = calculateBlockColor(zones, mRecord, i);
            mMBgPaint.setColor(color);
            canvas.drawRect(left, innerRingHeight, right, 0, mMBgPaint);
            canvas.rotate(mInnerDegrees, 0, 0);
        }
        canvas.restore();
    }

    private void drawInnerColorRing3(Canvas canvas) {
        if (mCurValue == -1 || mRecord[2] == 0) {
            return;
        }
        canvas.save();
        float startAngle = mInnerDegrees * (mRecord[0] - 1);
        canvas.rotate(startAngle);
        int count = mRecord[1] - mRecord[0] + 1;
        Log.d(TAG, "onDraw: count == " + count);
        int left = -(width >> 1) + outerRingHeight + innerRingPadding;
        int right = -(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth;
        for (int i = 0; i < count; i++) {
            /*------------ 找出开始角度的颜色值 -------------*/
            int color = calculateBlockColor(zone3, mRecord, i);
            mMBgPaint.setColor(color);
            canvas.drawRect(left, -innerRingHeight, right, 0, mMBgPaint);
            canvas.rotate(mInnerDegrees);
        }
        canvas.restore();
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
        int[] blockPositions;
        if (zones.length == 4){
            blockPositions = calculateBlockPositions3(zones);
        } else{
            blockPositions = calculateBlockPositions(zones);
        }
        // 五区间blockPositions.length为5,三区间为4
        int length = blockPositions.length;
        int cur = record[0] + i;
        // 找到当前区间
        int sectionPos = getSectionPos(blockPositions, cur, length);
        Log.d(TAG, "i == " + i);
        Log.d(TAG, "mCurValue == " + mCurValue + ";sectionPos == " + sectionPos + "; cur == " + cur);
        if (length == 5) {
            if (sectionPos == 1) {
                return Color.parseColor(FIRST);
            } else if (sectionPos == 2) {
                cur = cur - blockPositions[0];
                float factor = cur * 1.0f / (blockPositions[1] - blockPositions[0] +1);
                return ColorGradient.calculateColor(FIRST, SECOND, factor);
            } else if (sectionPos == 3) {
                cur = cur - blockPositions[1];
                float factor = cur * 1.0f / (blockPositions[2] - blockPositions[1] + 1);
                return ColorGradient.calculateColor(SECOND, THIRD, factor);
            } else if (sectionPos == 4) {
                cur = cur - blockPositions[2];
                float factor = cur * 1.0f / (blockPositions[3] - blockPositions[2] +1);
                return  ColorGradient.calculateColor(THIRD, FOURTH, factor);
            } else if (sectionPos == 5) {
                cur = cur - blockPositions[3];
                float factor = cur * 1.0f / (blockPositions[4] - blockPositions[3]+1);
                return ColorGradient.calculateColor(FOURTH, FIFTH, factor);
            } else {
                return Color.parseColor(FIFTH);
            }
        } else if (length == 4) {
            if (sectionPos == 1) {
                return Color.TRANSPARENT;
            } else if (sectionPos == 2) {
                return Color.parseColor(FIRST);
            } else if (sectionPos == 3) {
                return Color.parseColor(SECOND);
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
        if (length == 4) {
            if (cur < blockPositions[0]) {
                // 无颜色
                return 1;
            } else if (cur < blockPositions[1]) {
                //三区间第一个颜色
                return 2;
            } else if (cur < blockPositions[2]) {
                //三区间第二个颜色
                return 3;
            } else if (cur <= blockPositions[3]){
                // 三区间第三个颜色
                return 4;
            } else {
                // 无颜色
                return 1;
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

    private int[] calculateBlockPositions3(int[] zones) {
        int length = zones.length;
        int min = mMin;
        int max = mMax;
        int[] blockPositions = new int[length];
        for (int i = 0; i < length; i++) {
            blockPositions[i] = getValuePosition(min, max, zones[i], true);
        }
        return blockPositions;
    }

    private void drawArrow(Canvas canvas) {
        if (mCurValue == -1 || mCurValue < mMin) {
            return;
        }
        canvas.save();
        int value = mCurValue;
        if (value > mMax){
            value = mMax;
        }
        int valuePosition = getValuePosition(mMin, mMax, value, zone3 != null);
        float curAngle = mInnerDegrees * valuePosition + mInnerDegrees/2;
        canvas.rotate(curAngle);
        float fakeRad = (width >> 1) - outerRingHeight - innerRingPadding - innerRingWidth - (indicatorBp.getWidth() >> 1);
        mMatrix.reset();
        mMatrix.postRotate(30);
        mMatrix.postTranslate(-fakeRad, 0);

        mBitmapPaint.setColorFilter(new PorterDuffColorFilter(getCurrentValueColor(zones == null?zone3:zones, value), PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(indicatorBp, mMatrix, mBitmapPaint);
        canvas.restore();
    }

    /**
     * 画背景圆环以及起始标识圆点
     *
     * @param canvas canvas
     */
    private void drawBgRing(Canvas canvas) {
        mMBgPaint.setColor(0xFF484848);
        // 内环一个单位对应的角度
        mInnerDegrees = mSweepAngle * 1.0f / innerRingNumber;
        canvas.save();
        mPointPaint.setColor(zone3ColorResources[0]);
        canvas.drawCircle(-(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth +mPointRadian*2, -5,mPointRadian, mPointPaint);
        for (int i = 0; i < innerRingNumber; i++) {
            canvas.drawRect(-(width >> 1) + outerRingHeight + innerRingPadding, -innerRingHeight, -(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth, 0, mMBgPaint);
            canvas.rotate(mInnerDegrees);
        }
        mPointPaint.setColor(zone3ColorResources[2]);
        // width - 外环宽度- 内外环间距-内环宽度-画笔宽度-offset
        canvas.drawCircle(-(width >> 1) + outerRingHeight + innerRingPadding + innerRingWidth +mPointRadian*2, mPointRadian,mPointRadian, mPointPaint);
        canvas.restore();
    }

    /**
     * 画背景圆环以及起始标识圆点for
     *
     * @param canvas canvas
     */
    private void drawBgRing3(Canvas canvas) {
        mMBgPaint.setColor(0xFF484848);
        // 内环一个单位对应的角度
        mInnerDegrees = mSweepAngle * 1.0f / innerRingNumber;
        canvas.save();
        int below = zone3[1];
        int above = zone3[2];
        int belowPosition = getValuePosition(mMin, mMax, below, true);
        int abovePosition = getValuePosition(mMin, mMax, above, true);

        int averageWidth = width >> 1;
        for (int i = 0; i < innerRingNumber; i++) {
            canvas.drawRect(-averageWidth + outerRingHeight + innerRingPadding, -innerRingHeight, -averageWidth + outerRingHeight + innerRingPadding + innerRingWidth, 0, mMBgPaint);
            canvas.rotate(mInnerDegrees);
        }
        canvas.restore();
        canvas.save();
        int degrees = mStartAngle - 180;
        canvas.rotate(-degrees);
        if (mMin <= below) {
            float x = (float) (averageWidth * Math.cos((mStartAngle + belowPosition * mInnerDegrees) * Math.PI / 180));
            float y = (float) (averageWidth * Math.sin((mStartAngle + belowPosition * mInnerDegrees) * Math.PI / 180));
            mPointPaint.setColor(zone3ColorResources[0]);
            canvas.drawCircle(x, y, mPointRadian, mPointPaint);

            String belowValue = String.valueOf(below);
            mTargetPaint.getTextBounds(belowValue, 0, belowValue.length(), mRectF);
            mTargetPaint.setColor(zone3ColorResources[0]);
            canvas.drawText(belowValue, x - mRectF.width() - 2 * mPointRadian, y + (mRectF.height() >> 1), mTargetPaint);
        }
        if (above <= mMax) {
            mPointPaint.setColor(zone3ColorResources[2]);
            float x = (float) (averageWidth * Math.cos((mStartAngle + abovePosition * mInnerDegrees) * Math.PI / 180));
            float y = (float) (averageWidth * Math.sin((mStartAngle + abovePosition * mInnerDegrees) * Math.PI / 180));
            canvas.drawCircle(x, y, mPointRadian, mPointPaint);

            String aboveValue = String.valueOf(above);
            mTargetPaint.getTextBounds(aboveValue, 0, aboveValue.length(), mRectF);
            mTargetPaint.setColor(zone3ColorResources[2]);
            canvas.drawText(aboveValue, x + (3 * mPointRadian >> 1), y + (mRectF.height() >> 1), mTargetPaint);
        }
        canvas.restore();
    }
    /**
     * @param min   min value
     * @param max   max value
     * @param value 实时的value
     * @param isTargetSection 是否是三区间
     * @return 返回当前应该点亮的格子
     */
    private int getValuePosition(int min, int max, int value, boolean isTargetSection) {
        if (isTargetSection) {
            int below = zone3[1];
            int above = zone3[2];
            int centerZoneCount = above - below + 1;
            if (centerZoneCount <= 45) {
                //一个心率值占两格
                return 2 * (value - min + 1);
            } else {
                //一个心率值对应一格
                return value - min + 1;
            }
        }
        if (value == max) {
            return innerRingNumber;
        }
        int position = (value - min) * innerRingNumber / (max - min + 1) + 1;
        return position <= 0 ? 1 : position;
    }

    @ColorInt
    private int getCurrentValueColor(int[] zones, int curValue) {
        int color;
        int length = zones.length;
        if (length == 4) {
            if (curValue < zones[0]){
                return 0xFFFFFFFF;
            }
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
