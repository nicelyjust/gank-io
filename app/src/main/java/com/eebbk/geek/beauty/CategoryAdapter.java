package com.eebbk.geek.beauty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eebbk.geek.R;
import com.eebbk.geek.bean.netBean.DataInfoVo;

import java.util.ArrayList;
import java.util.List;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   CategoryAdapter
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/16 18:42
 *  @描述：    TODO 将之适配为每日精选,android ,ios等
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SparseIntArray mHeightmap;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<DataInfoVo> mDataInfoVos;

    public CategoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mHeightmap = new SparseIntArray();
        mDataInfoVos = new ArrayList<>();
    }
    public void setData(List<DataInfoVo> dataInfoVos){
        if (dataInfoVos != null) {
            int previousSize = mDataInfoVos.size();
            mDataInfoVos.clear();
            notifyItemRangeRemoved(0 , previousSize);
            mDataInfoVos.addAll(dataInfoVos);
            notifyItemRangeInserted(0 ,mDataInfoVos.size());
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_image, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataInfoVos == null || mDataInfoVos.size() <= position) {
            return;
        }
        DataInfoVo infoVo = mDataInfoVos.get(position);
        if (holder instanceof ImageHolder) {
            ImageView ivImage = ((ImageHolder) holder).mIvImage;
            //随机生成图片高度
            if (mHeightmap.size() <= position) {
                mHeightmap.put(position, generaHeight());
            }
            ViewGroup.LayoutParams lp = ivImage.getLayoutParams();
            //防止产生height为空
            int height = mHeightmap.get(position);
            if (height == 0) {
                height = generaHeight();
                mHeightmap.put(position, height);
            }
            lp.height = height;
            ivImage.setLayoutParams(lp);

            Glide.with(mContext).load(infoVo.getUrl())
                 .asBitmap()
                 .thumbnail(0.3f) // 缩略图
                 .into(ivImage);

        }
    }

    @Override
    public int getItemCount() {
        return mDataInfoVos == null ? 0 : mDataInfoVos.size();
    }

    /**
     * 随机生成瀑布流高度
     * @return
     */
    private int generaHeight() {
        return (int) (Math.random() * 300 + 500);
    }

    private class ImageHolder extends RecyclerView.ViewHolder {
        ImageView mIvImage;
        public ImageHolder(View itemView) {
            super(itemView);
            this.mIvImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    public void addAll(List<DataInfoVo> items) {
        if (items != null) {
            int size = mDataInfoVos.size();
            this.mDataInfoVos.addAll(items);
            notifyItemRangeInserted(size , items.size());
            notifyItemRangeChanged(size , items.size());
        }
    }
}
