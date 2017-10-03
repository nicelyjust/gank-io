package com.eebbk.nicely.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.eebbk.nicely.demo.R;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.view
 *  @创建者:   lz
 *  @创建时间:  2017/10/2 17:37
 *  @修改时间:  nicely 2017/10/2 17:37
 *  @描述：
 */

public class SquareLayout extends FrameLayout {
    private int mBaseDirection;

    public SquareLayout(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SquareLayout, defStyleAttr, defStyleRes);
        mBaseDirection = array.getInt(R.styleable.SquareLayout_oscAccordTo, 3);
        array.recycle();
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mBaseDirection == 1) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else if (mBaseDirection == 2) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        } else {
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            if (heightSize == 0) {
                super.onMeasure(widthMeasureSpec, widthMeasureSpec);
                return;
            }

            if (widthSize == 0) {
                super.onMeasure(heightMeasureSpec, heightMeasureSpec);
                return;
            }

            if (widthSize > heightSize)
                super.onMeasure(heightMeasureSpec, heightMeasureSpec);
            else
                super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }
    }
}
