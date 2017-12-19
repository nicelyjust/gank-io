package com.eebbk.geek.news.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment2;
import com.eebbk.geek.bean.netBean.DataInfoVo;
import com.eebbk.geek.beauty.CategoryAdapter;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.news.presenter.NewsPresenter;
import com.eebbk.geek.utils.L;
import com.eebbk.geek.utils.TDevice;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.home.ui
 *  @文件名:   NewsFragment
 *  @创建者:   Administrator
 *  @创建时间: 2017/11/7 16:19
 *  @描述：
 */

public class NewsFragment extends LazyBaseFragment2 implements BGARefreshLayout.BGARefreshLayoutDelegate,NewsView {

    private static final String TAG = "NewsFragment";
    @BindView(R.id.rv_news)
    RecyclerView     mRv;
    @BindView(R.id.refresh_layout_news)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.pb_news)
    ProgressBar mProgressBar;
    private NewsPresenter mPresenter;
    private String mCategory;
    private CategoryAdapter mAdapter;

    public static NewsFragment newInstance(String category) {
        Bundle args = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        args.putString("category" , category);
        newsFragment.setArguments(args);
        return newsFragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        mCategory = bundle.getString("category");
    }

    @Override
    protected void initWidget(View root) {
        createP();
        initPullToRefresh();
        initRV();
    }

    private void initRV() {
        mRv.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new CategoryAdapter(mContext);
        mRv.setAdapter(mAdapter);
    }

    private void initPullToRefresh() {
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder( getActivity() , true);
        refreshViewHolder.setOriginalImage(R.mipmap.ic_nav_news_normal);
        refreshViewHolder.setUltimateColor(R.color.main_green);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    private void createP() {
        mPresenter = new NewsPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void fetchData() {
        L.d(TAG,"fetchData");
        showLoading();
        mPresenter.loadNewsData(Constant.LOAD_TYPE_NORMAL , mCategory);
    }

    @Override
    public void loadData(List<DataInfoVo> dataInfoVos) {
        mAdapter.setData(dataInfoVos);
    }
    @Override
    public void onResume() {
        super.onResume();
        L.d(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        L.d(TAG);
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
    public void showPullRefreshOk() {
        if (mRefreshLayout != null) {
            mRefreshLayout.endRefreshing();
        }
    }

    @Override
    public void loadDataMore(List<DataInfoVo> dataInfoVos) {
        mAdapter.addAll(dataInfoVos);
    }

    @Override
    public void showLoadMoreOk() {
        if (mRefreshLayout != null && mRefreshLayout.isLoadingMore() ) {
            mRefreshLayout.endLoadingMore();
        }
    }
    /*------------ UI -------------*/
    @Override
    public void showLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getActivity().getWindow().getDecorView() , message , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int messageRes) {
        Snackbar.make(getActivity().getWindow().getDecorView() , messageRes , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.destroy();
        }
    }

}
