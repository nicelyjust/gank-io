package com.eebbk.geek.beauty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eebbk.geek.R;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.web.WebActivity;

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

    private static final int TYPE_NEWS = 0x01;
    private static final int TYPE_IMAGE = 0x02;

    private SparseIntArray mHeightmap;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<DataInfoVo> mDataInfoVos;
    private static final String TAG = "CategoryAdapter";

    public CategoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mHeightmap = new SparseIntArray();
        mDataInfoVos = new ArrayList<>();
    }

    public void setData(List<DataInfoVo> dataInfoVos) {
        if (dataInfoVos != null) {
            int previousSize = mDataInfoVos.size();
            mDataInfoVos.clear();
            notifyItemRangeRemoved(0, previousSize);
            mDataInfoVos.addAll(dataInfoVos);
            notifyItemRangeInserted(0, mDataInfoVos.size());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_IMAGE:
                view = mInflater.inflate(R.layout.item_image, parent, false);
                return new ImageHolder(view);
            case TYPE_NEWS:
                view = mInflater.inflate(R.layout.item_category, parent, false);
                return new NewsHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataInfoVos == null || mDataInfoVos.size() <= position) {
            return;
        }
        DataInfoVo infoVo = mDataInfoVos.get(position);
        if (holder instanceof ImageHolder) {
            bindImageHolder((ImageHolder) holder, position, infoVo);
        } else if (holder instanceof NewsHolder) {
            bindNewsHolder((NewsHolder) holder, infoVo ,position);
        }
    }

    private void bindNewsHolder(NewsHolder vh, DataInfoVo infoVo, int position) {
        vh.itemView.setTag(position);
        List<String> images = infoVo.getImages();
        if (images == null || images.isEmpty() || TextUtils.isEmpty(images.get(0))) {
            vh.mIvImage.setVisibility(View.GONE);
        } else {
            Glide.with(mContext).load(images.get(0))
                    .dontAnimate()
                    .thumbnail(0.3f) // 缩略图
                    .into(vh.mIvImage);
        }
        vh.itemView.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            WebActivity.start(v.getContext(),infoVo.getUrl());
        });
        vh.mTvAuthor.setText(infoVo.getWho());
        vh.mTvPublishTime.setText(infoVo.getPublishedTime());
        vh.mTvDesc.setText(infoVo.getDesc());
    }

    private void bindImageHolder(ImageHolder holder, int position, DataInfoVo infoVo) {
        ImageView ivImage = holder.mIvImage;
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

    @Override
    public int getItemCount() {
        return mDataInfoVos == null ? 0 : mDataInfoVos.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataInfoVos == null || mDataInfoVos.size() == 0) {
            return TYPE_NEWS;
        }
        String type = mDataInfoVos.get(position).getType();
        Log.d(TAG, "type = " + type);
        if (Constant.Category.IMAGE.equals(type)) {
            return TYPE_IMAGE;
        } else {
            return TYPE_NEWS;
        }
    }

    /**
     * 随机生成瀑布流高度
     *
     * @return
     */
    private int generaHeight() {
        return (int) (Math.random() * 300 + 500);
    }

    static class ImageHolder extends RecyclerView.ViewHolder {
        ImageView mIvImage;

        ImageHolder(View itemView) {
            super(itemView);
            this.mIvImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    public void addAll(List<DataInfoVo> items) {
        if (items != null) {
            int size = mDataInfoVos.size();
            this.mDataInfoVos.addAll(items);
            notifyItemRangeInserted(size, items.size());
            notifyItemRangeChanged(size, items.size());
        }
    }

    static class NewsHolder extends RecyclerView.ViewHolder {
//        RelativeLayout mRootView;
        ImageView mIvImage;
        TextView mTvDesc;
        TextView mTvAuthor;
        TextView mTvPublishTime;
        public NewsHolder(View itemView) {
            super(itemView);
//            mRootView = (RelativeLayout) itemView.findViewById(R.id.item_root_news);
            mIvImage = (ImageView) itemView.findViewById(R.id.iv_news_image);
            mTvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mTvPublishTime = (TextView) itemView.findViewById(R.id.tv_publish_time);
        }
    }

}
