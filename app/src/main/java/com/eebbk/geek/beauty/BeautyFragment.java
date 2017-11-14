package com.eebbk.geek.beauty;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment2;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.geek.beauty
 *  @文件名:   BeautyFragment
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/14 12:32
 *  @描述：    TODO
 */

public class BeautyFragment extends LazyBaseFragment2 implements BGARefreshLayout.BGARefreshLayoutDelegate , BeautyView{

    @BindView(R.id.rv_beauty)
    RecyclerView     mRv;
    @BindView(R.id.refresh_layout_beauty)
    BGARefreshLayout mRefreshLayout;
    private BeautyPresenter mPresenter;

    public static BeautyFragment newInstance() {
        BeautyFragment fragment = new BeautyFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_beauty;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        createP();
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder(getActivity() , true);
        refreshViewHolder.setOriginalImage(R.mipmap.ic_nav_news_normal);
        refreshViewHolder.setUltimateColor(R.color.main_green);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void createP() {
        mPresenter = new BeautyPresenter(getActivity());
    }
    @Override
    protected void fetchData() {
//        mPresenter.loadData();
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
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

    }
}
