package com.eebbk.geek.beauty;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment2;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.media.ImageAdapter;
import com.eebbk.geek.media.SpaceGridItemDecoration;
import com.eebbk.geek.media.config.ImageLoaderListener;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   BeautyFragment
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 12:32
 *  @描述：
 */

public class BeautyFragment extends LazyBaseFragment2 implements BGARefreshLayout.BGARefreshLayoutDelegate , BeautyView, {

    @BindView(R.id.rv_beauty)
    RecyclerView     mRv;
    @BindView(R.id.refresh_layout_beauty)
    BGARefreshLayout mRefreshLayout;
    private BeautyPresenter mPresenter;
    private String mCategory;

    public static BeautyFragment newInstance(String category) {
        Bundle args = new Bundle();
        BeautyFragment fragment = new BeautyFragment();
        args.putString(Constant.Extra.CATEGORY , category);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_beauty;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        mCategory = bundle.getString(Constant.Extra.CATEGORY);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        createP();
        initPullToRefresh();
        initRecyclerview();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void initRecyclerview() {
        mRv.setLayoutManager(new GridLayoutManager(mContext , 2));
        mRv.addItemDecoration(new SpaceGridItemDecoration());
        mRv.setAdapter();
    }

    private void initPullToRefresh() {
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity() , true);
        refreshViewHolder.setOriginalImage(R.mipmap.ic_nav_discover_normal);
        refreshViewHolder.setUltimateColor(R.color.main_green);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    private void createP() {
        mPresenter = new BeautyPresenter(getActivity());
    }
    @Override
    protected void fetchData() {
        showLoading();
        mPresenter.loadData(mCategory , Constant.LOAD_TYPE_NORMAL);
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//        mPresenter.loadData(mCategory , Constant.LOAD_TYPE_DOWN);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

//        mPresenter.loadData(mCategory , Constant.LOAD_TYPE_UP);
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mContext , message ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void loadData(List<DataInfoVo> dataInfoVoList) {

    }
}
