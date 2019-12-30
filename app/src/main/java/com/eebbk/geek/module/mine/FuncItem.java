package com.eebbk.geek.module.mine;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.eebbk.geek.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.mine
 *  @文件名:   FuncItem
 *  @创建者:   lz
 *  @创建时间:  2019/12/30 15:53
 *  @描述：
 */

public class FuncItem extends LinearLayout {
    @BindView(R.id.iv_func_icon)
    ImageView mIvFuncIcon;
    @BindView(R.id.tv_func_name)
    TextView mTvFuncName;
    @BindView(R.id.v_func_line)
    View mVFuncLine;
    @BindView(R.id.rl_func)
    RelativeLayout mRlFunc;
    private String mName;

    public FuncItem(Context context) {
        this(context, null);
    }

    public FuncItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FuncItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public FuncItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_func_item, this);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.FuncItem);
        mName = ta.getString(R.styleable.FuncItem_fi_name);
        mName = TextUtils.isEmpty(mName) ? "Function Name" : mName;
        ta.recycle();
        ButterKnife.bind(this, view);
        mTvFuncName.setText(mName);
    }

    public void setImageResource(@DrawableRes int resId) {
        mIvFuncIcon.setImageResource(resId);
    }

    public void setText(@StringRes int resId) {
        mTvFuncName.setText(resId);
    }

    public void setText(CharSequence text) {
        mTvFuncName.setText(text);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        mRlFunc.setOnClickListener(l);
    }

    /**
     * 設置func 两边的图标
     *
     * @param start Resource identifier of the start Drawable.
     * @param end   Resource identifier of the end Drawable.
     */
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(@DrawableRes int start, @DrawableRes int end) {
        mTvFuncName.setCompoundDrawablesRelativeWithIntrinsicBounds(start, 0, end, 0);
    }

    /**
     * @param pad px
     */
    public void setCompoundDrawablePadding(int pad) {
        mTvFuncName.setCompoundDrawablePadding(pad);
    }

    public void setLineVisibility(int visibility) {
        mVFuncLine.setVisibility(visibility);
    }

}
