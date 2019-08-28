package com.eebbk.geek.media;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eebbk.geek.media.bean.Image;
import com.eebbk.geek.media.config.ImageLoaderListener;
import com.eebbk.geek.R;

import java.util.List;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.nicely.demo.media
 *  @创建者:   lz
 *  @创建时间:  2017/10/2 13:23
 *  @修改时间:  nicely 2017/10/2 13:23
 *  @描述：
 */

public class ImageAdapter extends RecyclerView.Adapter {

    private List<Image>         mImages;
    private Context             mContext;
    private ImageLoaderListener mListener;
    private LayoutInflater      mInflater;
    private boolean isSingleSelect = true;
    private OnClickListener mOnClickListener;

    public void setSingleSelect(boolean isSingleSelect) {
        this.isSingleSelect = isSingleSelect;
    }

    public ImageAdapter(@NonNull Context context , @NonNull ImageLoaderListener listener) {
        mContext = context;
        mListener = listener;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<Image> image) {
        mImages = image;
    }

    public List<Image> getDatas() {
        return mImages;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new CamHolder(mInflater.inflate(R.layout.item_list_cam, parent, false));
        } else {
            return new ImageHolder(mInflater.inflate(R.layout.item_list_image, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Image image = mImages.get(position);
        if (image.getId() != 0) {
            ImageHolder h = (ImageHolder) holder;
            h.mCbSelected.setSelected(image.isSelect());
            h.mMask.setVisibility(image.isSelect() ? View.VISIBLE : View.GONE);
            mListener.displayImg(h.mImage , image.getPath());
            h.mIvGif.setVisibility(image.getPath().endsWith(".gif") ? View.VISIBLE : View.GONE);
            h.mCbSelected.setVisibility(isSingleSelect ? View.GONE : View.VISIBLE);
            h.itemView.setTag(position);
            h.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onItemClickListener((int) v.getTag());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mImages == null || mImages.isEmpty() ? 0 : mImages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mImages.get(position).getId() == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof ImageHolder) {
            ImageHolder h = (ImageHolder) holder;
            Glide.clear(h.mImage);
        }

    }

    private static class CamHolder extends RecyclerView.ViewHolder {
        CamHolder(View itemView) {
            super(itemView);
        }
    }

    private static class ImageHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        View mMask;
        ImageView mCbSelected;
        ImageView mIvGif;

        ImageHolder(View itemView) {
            super(itemView);
            this.mImage = (ImageView) itemView.findViewById(R.id.iv_image);
            this.mMask =  itemView.findViewById(R.id.lay_mask);
            this.mCbSelected = (ImageView) itemView.findViewById(R.id.cb_selected);
            this.mIvGif = (ImageView) itemView.findViewById(R.id.iv_is_gif);
        }
    }
}
