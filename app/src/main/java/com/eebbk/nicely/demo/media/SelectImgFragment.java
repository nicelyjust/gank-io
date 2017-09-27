package com.eebbk.nicely.demo.media;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.media.config.SelectOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.media
 *  @文件名:   SelectImgFragment
 *  @创建者:   lz
 *  @创建时间:  2017/9/26 11:22
 *  @修改时间:  Administrator 2017/9/26 11:22 
 *  @描述：    TODO
 */
public class SelectImgFragment extends Fragment
{
    private static final String TAG = "SelectImgFragment";
    private static SelectOptions mOptions;
    @BindView(R.id.icon_back)
    ImageView      mIconBack;
    @BindView(R.id.iv_title_select)
    ImageView      mIvTitleSelect;
    @BindView(R.id.btn_title_select)
    Button         mBtnTitleSelect;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.rv_img_select)
    RecyclerView   mRv;
    private Unbinder mBind;

    public static SelectImgFragment newInstance(SelectOptions options) {
        mOptions = options;

        return new SelectImgFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragemnt_select_img, container, false);
        mBind = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
