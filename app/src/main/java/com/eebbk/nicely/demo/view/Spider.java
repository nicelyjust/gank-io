package com.eebbk.nicely.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.utils.DimentionUtils;

import java.util.List;

/*
 *  @项目名：  Pad_VideoTraining 
 *  @包名：    com.eebbk.vtraining.view
 *  @文件名:   Spider
 *  @创建者:   lz
 *  @创建时间:  2017/8/17 15:33
 *  @修改时间:  Administrator 2017/8/17 15:33 
 *  @描述：    下拉框封装
 */
public class Spider extends LinearLayout implements View.OnClickListener {
    private static final String TAG         = "Spider";
    private static final long TIME_GRADING  = 200;
    private static final long TIME_POPWINDOW = 250;
    private Context                      mContext;
    private LayoutInflater               mLayoutInflater;
    private TextView                     mTxtView;
    private ImageView                    mArrow;
    private List<? extends BaseSpiderBean> mList;
    private String                       mCurTxt ;
    private PopupWindow                  mPopupWindow;
    private OnSpiderListener             mOnOnSpiderListener;
    private LinearLayout                 mRoot;
    // xml设置的是DP值,这里需要转化为px
    private int                          mPopupHeight ;
    private int                          mPopupTxtColor;
    private int                          mPopupTxtColored;
    private ColorDrawable                mDw;
    private RecyclerView                 mRecyclerView;
    private RelativeLayout               mPopupRoot;
    private SpiderAdapter                mAdapter;

    private int mLastPos;
    public Spider(Context context) {
        this(context , null);
    }

    public Spider(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public Spider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.Spinner);
        mPopupTxtColor = typedArray.getColor(R.styleable.spinner_popupTxtColor, Color.WHITE);
        mPopupTxtColored = typedArray.getColor(R.styleable.spinner_popupTxtColored, Color.BLUE);
        float      dimPx  = typedArray.getDimension(R.styleable.spinner_popupHeight, 250);
        mPopupHeight = DimentionUtils.dip2px(mContext , dimPx);
        typedArray.recycle();

        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initView() {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.spider, this);
        mRoot = (LinearLayout) view.findViewById(R.id.spider_root);
        mTxtView = (TextView) view.findViewById(R.id.spider_txt);
        mArrow = (ImageView) view.findViewById(R.id.spider_arrow);
        mRoot.setOnClickListener(this);

        initPopupWindow();
    }

    private void initPopupWindow() {
        LayoutInflater inflater  = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View    viewPopup = inflater.inflate(R.layout.spider_popup, null ,false);

        mPopupRoot = (RelativeLayout) viewPopup.findViewById(R.id.spider_popup_root);
        mRecyclerView = (RecyclerView)viewPopup.findViewById(R.id.spider_popup_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SpiderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        initPopupParams(viewPopup);
    }

    public  void setOnSpiderListener (@NonNull OnSpiderListener onOnSpiderListener) {

        mOnOnSpiderListener = onOnSpiderListener;
    }

    public  void  setData(@NonNull List<? extends BaseSpiderBean> list) {
        mList = list;
        if (mTxtView != null && !mList.isEmpty()) {
            mCurTxt = mList.get(0).getSpiderName();
            mList.get(0).setSelectState(true);
            mTxtView.setText(mCurTxt);
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initPopupParams(View view) {
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(view, 300, mPopupHeight);
            mDw = new ColorDrawable(0x000000);
        }
        mPopupWindow.setFocusable(false);
        mPopupWindow.setBackgroundDrawable(mDw);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mOnOnSpiderListener != null) {
                    mOnOnSpiderListener.onDismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spider_root:
                if (mList == null || mList.isEmpty()) {
                    return;
                }
                if (mPopupWindow.isShowing()) {
                    // 收缩
                    popupWindowBack();
                } else {
                    // 展开
                    mPopupWindow.showAsDropDown(mTxtView);
                    PopupWindowIn();
                }

                 break;
            default:
                 break;
        }
    }

    public void PopupWindowIn(){
        mPopupWindow.getContentView().animate().translationY(0).setDuration(TIME_POPWINDOW);
        mArrow.animate().rotation(180).setDuration(TIME_GRADING);
    }

    public void popupWindowBack(){
        mArrow.animate().rotation(360).setDuration(TIME_GRADING);
        mPopupWindow.getContentView().animate().translationY(-mPopupHeight).setDuration(TIME_POPWINDOW);
        mArrow.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPopupWindow.dismiss();
            }
        }, TIME_POPWINDOW );
    }

    private class SpiderAdapter extends RecyclerView.Adapter<SpiderHolder> {

        @Override
        public SpiderHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View         inflate = mLayoutInflater.inflate(R.layout.item_select_spider, parent, false);
            return new SpiderHolder(inflate);
        }

        @Override
        public void onBindViewHolder(SpiderHolder holder,  int position) {
            BaseSpiderBean spiderBean = mList.get(position);
            if (spiderBean.getIsSelectState()) {
                holder.mTxtView.setTextColor(mPopupTxtColored);
            } else {
                holder.mTxtView.setTextColor(mPopupTxtColor);
            }
            holder.mTxtView.setTag(position);
            holder.mTxtView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    if (mLastPos != pos) {
                        mList.get(mLastPos).setSelectState(!mList.get(mLastPos).getIsSelectState());
                        mList.get(pos).setSelectState(!mList.get(pos).getIsSelectState());
                        notifyItemChanged(mLastPos);
                        notifyItemChanged(pos);
                        mCurTxt = mList.get(pos).getSpiderName();
                        if (mTxtView != null) {
                            mTxtView.setText(mCurTxt);
                        }
                        mLastPos = pos;
                    }
                    if (mPopupWindow != null) {
                        mPopupWindow.dismiss();
                    }
                    if (mOnOnSpiderListener != null) {
                        mOnOnSpiderListener.onItemClick(pos);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }
    }

    private class SpiderHolder extends RecyclerView.ViewHolder {
        public TextView mTxtView;
        public SpiderHolder(View itemView) {
            super(itemView);
            mTxtView = (TextView) itemView.findViewById(R.id.tv_item_name);
        }
    }
    public interface OnSpiderListener {
        void onDismiss();
        void onItemClick(int position);
    }
}
