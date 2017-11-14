package com.eebbk.geek.media;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eebbk.geek.bean.CityBean;

import java.util.List;

/**
 * Created by huanghaibin
 * on 16-5-9.
 */
public class SpaceGridItemDecoration extends RecyclerView.ItemDecoration {
    private int            space;
    private int            mHeight;
    private List<CityBean> mDatas;

    public SpaceGridItemDecoration(int space) {
        this.space = space;
    }

    public SpaceGridItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
        super.getItemOffsets(outRect , view , parent , state);
        /*int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position == 0) {
            outRect.set(0 , mHeight , 0 , 0);
        } else if (position > 0){
            if (mDatas.get(position).getTag() != null && !mDatas.get(position).getTag().equals(mDatas.get(position - 1).getTag())) {
                outRect.set(0 , mHeight , 0 , 0);
            } else {
                outRect.set(0 , 0 , 0 , 0);
            }
        }*/

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
