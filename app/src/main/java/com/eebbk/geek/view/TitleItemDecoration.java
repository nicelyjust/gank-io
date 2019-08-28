package com.eebbk.geek.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


/*
 *  @项目名：  Pad_VideoTraining 
 *  @包名：    com.eebbk.vtraining.view
 *  @文件名:   TitleItemDecoration
 *  @创建者:   lz
 *  @创建时间:  2017/12/28 19:39
 *  @描述：    TODO
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {
    //设置ItemView的内嵌偏移长度（inset）
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        if (parent.getLayoutManager() instanceof GridLayoutManager) {

        } else if (parent.getLayoutManager() instanceof LinearLayoutManager && !isLastRow(position , parent)) {
            outRect.set(0, 0, 0, 10);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }

    }
    // 绘制图层在ItemView以下，所以如果绘制区域与ItemView区域相重叠，会被遮挡
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
    // 同样是绘制内容，但与onDraw（）的区别是：绘制在图层的最上层,用于制造悬浮的效果
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 是否最后一行
     */
    public boolean isLastRow(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int itemCount = parent.getAdapter().getItemCount();
            if ((itemCount - itemPosition - 1) < spanCount)
                return true;
        } else if (layoutManager instanceof LinearLayoutManager) {
            return parent.getAdapter().getItemCount() -1 == itemPosition;
        }
        return false;
    }
    /**
     * 是否最后span
     */
    public boolean isLastSpan(int itemPosition, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            if ((itemPosition + 1) % spanCount == 0)
                return true;
        }
        return false;
    }

}
