package com.eebbk.geek.beauty;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.beauty.p.BeautyPresenter;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.media.SpaceGridItemDecoration;
import com.eebbk.geek.utils.TDevice;

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

public class BeautyFragment extends LazyBaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate , BeautyView
{

    @BindView(R.id.rv_beauty)
    RecyclerView     mRv;
    @BindView(R.id.refresh_layout_beauty)
    BGARefreshLayout mRefreshLayout;
    private BeautyPresenter mPresenter;
    private String          mCategory;
    private CategoryAdapter mAdapter;

    public static BeautyFragment newInstance(String category) {
        Bundle args = new Bundle();
        BeautyFragment fragment = new BeautyFragment();
        args.putString(Constant.Extra.CATEGORY , category);
        fragment.setArguments(args);
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
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL));
        mRv.addItemDecoration(new SpaceGridItemDecoration(TDevice.dip2px(mContext , 5)));
        mAdapter = new CategoryAdapter(mContext);
        mRv.setAdapter(mAdapter);
    }

    private void initPullToRefresh() {
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity() , true);
        refreshViewHolder.setOriginalImage(R.mipmap.ic_nav_discover_normal);
        refreshViewHolder.setUltimateColor(R.color.main_green);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    private void createP() {
        mPresenter = new BeautyPresenter();
    }
    @Override
    protected void fetchData() {
        showLoading();
        mPresenter.loadData(mCategory , Constant.LOAD_TYPE_NORMAL);
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (TDevice.hasInternet()) {
            mPresenter.pullToRefresh(mCategory ,false);
        } else {
            showMessage(R.string.no_internet);
            mRefreshLayout.endRefreshing();
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (TDevice.hasInternet()) {
            mPresenter.pullToRefresh(mCategory ,true);
            return true;
        } else {
            showMessage(R.string.no_internet);
            return false;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(mContext , message ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int messageRes) {
        Toast.makeText(mContext , messageRes ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadData(List<DataInfoVo> dataInfoVoList) {
        mAdapter.setData(dataInfoVoList);
    }

    @Override
    public void loadDataMore(List<DataInfoVo> dataInfoVoList) {
        mAdapter.addAll(dataInfoVoList);
    }
    @Override
    public void showLoadMoreOk() {
        if (mRefreshLayout != null && mRefreshLayout.isLoadingMore() ) {
            mRefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void showPullRefreshOk() {
        if (mRefreshLayout != null) {
            mRefreshLayout.endRefreshing();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
