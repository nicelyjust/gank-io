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
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.eebbk.geek.R;
import com.eebbk.geek.module.viewLearn.UnitFormat;

import java.util.Arrays;


/*
 *  @项目名：  mio_sport
 *  @包名：    com.lifesense.mio.component.widget
 *  @文件名:   HorizontalRatioView
 *  @创建者:   lz
 *  @创建时间:  2020/3/18 15:11
 *  @描述：1.确定三区间 or 五区间;2.
 */
public class HorizontalRatioView extends View {
    private static final String TAG = "HorizontalRatioView";

    private static final String FIRST = "#FF4FBFED";
    private static final String SECOND = "#FF05E16B";
    private static final String THIRD = "#FFFE8C29";
    private static final String FOURTH = "#FFFF40E2";
    private static final String FIFTH = "#FFEE291C";

    private static final String DEFAULT_COLOR = "#FF484848";

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
    private int[] mZones;
    /**
     * 统计每一段的时间s
     */
    private int[] mCounts;
    private Paint mPaint;

    private int horizontalMargin = dip2px(15);
    private int progressHeight = dip2px(6);
    private int mWidth;
    private int mHeight;
    private int mCenterY;
    /**
     * 当前的总时长
     */
    private int mTotal;
    /**
     * 每一段的位置
     */
    private float[] mPositions;
    /**
     * 测量文字用
     */
    private Rect mRectF = new Rect();

    private TextPaint mTextPaint;
    /**
     * 变换indicator位置
     */
    private Matrix mMatrix;
    private Paint mBitmapPaint;
    private Bitmap indicatorBp;

    public HorizontalRatioView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalRatioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HorizontalRatioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        indicatorBp = BitmapFactory.decodeResource(getResources(), R.mipmap.today_live_checkmark);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(progressHeight);
        mPaint.setStrokeCap(Paint.Cap.BUTT);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(dip2px(13));
        mTextPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro-Regular.otf"));

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmapPaint.setDither(true);

        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mCenterY = mHeight / 2;
    }

    /**
     * 需初始化
     *
     * @param zones length为4,三区间;length为6,五区间
     */
    public void setZones(@NonNull int[] zones) {
        this.mZones = zones;
        this.mCounts = new int[mZones.length];
        this.mCounts[mZones.length - 1] = -1;
        this.mPositions = new float[mZones.length];
    }

    /**
     * 判断当前的值属于哪个区间,当前count+1
     *
     * @param liveValue 若为负数,代表暂停时候心率
     */
    public void setLiveValue(int liveValue, int total) {
        this.mTotal = total;
        // 当前所处的的区间
        int currentSection = mCounts.length - 1;
        if (mZones.length == 4) {
            if (liveValue < mZones[0]) {
                mCounts[currentSection] = -1;
                Log.d(TAG, "三区间 setLiveValue: " + liveValue);
            } else if (liveValue < mZones[1]) {
                mCounts[0]++;
                mCounts[currentSection] = 0;
            } else if (liveValue < mZones[2]) {
                mCounts[1]++;
                mCounts[currentSection] = 1;
            } else {
                mCounts[2]++;
                mCounts[currentSection] = 2;
            }
        } else if (mZones.length == 6) {
            if (liveValue < mZones[0]) {
                mCounts[currentSection] = -1;
                Log.d(TAG, "五区间 setLiveValue: " + liveValue);
            } else if (liveValue < mZones[1]) {
                mCounts[0]++;
                mCounts[currentSection] = 0;
            } else if (liveValue < mZones[2]) {
                mCounts[1]++;
                mCounts[currentSection] = 1;
            } else if (liveValue < mZones[3]) {
                mCounts[2]++;
                mCounts[currentSection] = 2;
            } else if (liveValue < mZones[4]) {
                mCounts[3]++;
                mCounts[currentSection] = 3;
            } else {
                mCounts[4]++;
                mCounts[currentSection] = 4;
            }
        }
        mPositions[0] = horizontalMargin;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(0, mCenterY);
        mPaint.setColor(Color.parseColor(DEFAULT_COLOR));
        canvas.drawLine(horizontalMargin,0,mWidth-horizontalMargin,0,mPaint);

        drawProgress(canvas);
        drawIndicator(canvas);
        drawDuration(canvas);
    }

    /**
     * 绘制每一段占比 AND 绘制每一段百分比,低于百分之6%不绘制
     */
    private void drawProgress(Canvas canvas) {
        if (mZones == null || mCounts == null) {
            return;
        }

        int length = mZones.length;
        int totalWidth = mWidth - 2 * horizontalMargin;
        mTextPaint.setTextSize(dip2px(13));
        if (length == 4) {
            int count = mCounts.length - 1;
            for (int i = 0; i < count; i++) {
                int x = mCounts[i];
                if (x == 0){
                    // 当前区间值为零则让之等于上一区间值
                    mPositions[i + 1] = mPositions[i];
                    continue;
                }
                float ratio = x * 1.0f / mTotal;
                mPositions[i + 1] = mPositions[i] + ratio * totalWidth;

                mPaint.setColor(zone3ColorResources[i]);
                canvas.drawLine(mPositions[i],0,mPositions[i + 1],0,mPaint);
                int percent = (int) (ratio * 100);
                if (percent >= 6) {
                    String text = String.valueOf(percent).concat("%");
                    mTextPaint.getTextBounds(text, 0, text.length(), mRectF);
                    mTextPaint.setColor(zone5ColorResources[i]);
                    canvas.drawText(text, mPositions[i + 1] - mRectF.width(), dip2px(7) + mRectF.height(), mTextPaint);
                }
            }

            Log.d(TAG, "drawProgress: mPositions == " + Arrays.toString(mPositions));
            Log.d(TAG, "drawProgress: mWidth == " + (mWidth - 2 * horizontalMargin));
            return;
        }
        if (length == 6) {
            int count = mCounts.length - 1;
            for (int i = 0; i < count; i++) {
                int x = mCounts[i];
                if (x == 0){
                    // 当前区间值为零则让之等于上一区间值
                    mPositions[i + 1] = mPositions[i];
                    continue;
                }
                float ratio = x * 1.0f / mTotal;
                mPositions[i + 1] = mPositions[i] + ratio * totalWidth;
                mPaint.setColor(zone5ColorResources[i]);
                canvas.drawLine(mPositions[i],0,mPositions[i + 1],0,mPaint);
                int percent = (int) (ratio * 100);
                if (percent >= 6) {
                    String text = String.valueOf(percent).concat("%");
                    mTextPaint.getTextBounds(text, 0, text.length(), mRectF);
                    mTextPaint.setColor(zone5ColorResources[i]);
                    canvas.drawText(text, mPositions[i + 1] - mRectF.width(), dip2px(7) + mRectF.height(), mTextPaint);
                }
            }

            Log.d(TAG, "drawProgress: mPositions == " + Arrays.toString(mPositions));
            Log.d(TAG, "drawProgress: mWidth == " + (mWidth - 2 * horizontalMargin));
        }


        //判断最后一个点的坐标

    }

    private void drawIndicator(Canvas canvas) {
        if (mZones == null || mCounts == null) {
            return;
        }
        int current = mCounts[mCounts.length - 1];
        if (mZones.length < 6 || current == -1) {
            //只有五区间需要绘制上方指示器,当前区间是灰色以外的区间也pass
            return;
        }
        float currentX = mPositions[current] + (mPositions[current + 1] - mPositions[current]) / 2;
        float currentY = progressHeight + indicatorBp.getHeight();
        mMatrix.reset();
        mMatrix.postTranslate(currentX, -currentY);

        mBitmapPaint.setColorFilter(new PorterDuffColorFilter(zone5ColorResources[current], PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(indicatorBp, mMatrix, mBitmapPaint);
    }

    /**
     * 判断当前的长度是否能够显示下时间
     *
     * @param canvas
     */
    private void drawDuration(Canvas canvas) {
        if (mZones == null || mCounts == null) {
            return;
        }
        int current = mCounts[mCounts.length - 1];
        if (mZones.length > 4 || current == -1) {
            //只有三区间需要绘制时间
            return;
        }
        String text = UnitFormat.formatLiveDuration(mCounts[current]);
        mTextPaint.setTextSize(dip2px(18));
        mTextPaint.getTextBounds(text, 0, text.length(), mRectF);
        mTextPaint.setColor(zone3ColorResources[current]);
        float currentWidth = mPositions[current + 1] - mPositions[current];
        int posY = -dip2px(10);
        if (currentWidth >= mRectF.width()) {
            canvas.drawText(text, mPositions[current + 1] - mRectF.width(), posY, mTextPaint);
        } else {
            // TODO: 2020/3/19 存在灰色区间的时候,这样判断会有问题
            int threshold = mWidth - horizontalMargin;
            if ((mPositions[current] + mRectF.width()) < threshold) {
                canvas.drawText(text, threshold - mRectF.width(), posY, mTextPaint);
            } else {
                canvas.drawText(text, mPositions[current], posY, mTextPaint);
            }

        }
    }

    private static int dip2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
