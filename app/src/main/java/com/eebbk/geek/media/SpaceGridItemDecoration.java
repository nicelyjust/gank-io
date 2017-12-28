package com.eebbk.geek.media;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by huanghaibin
 * on 16-5-9.
 */
public class SpaceGridItemDecoration extends RecyclerView.ItemDecoration {
    private int            space;

    public SpaceGridItemDecoration(int space) {
        this.space = space;
    }

    public SpaceGridItemDecoration() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect , view , parent , state);
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

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
