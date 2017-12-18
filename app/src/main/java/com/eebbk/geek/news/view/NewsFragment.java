package com.eebbk.geek.news.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.news.presenter.NewsPresenter;
import com.eebbk.geek.utils.L;

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

public class NewsFragment extends LazyBaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.rv_news)
    RecyclerView     mRv;
    @BindView(R.id.refresh_layout_news)
    BGARefreshLayout mRefreshLayout;
    private static final String TAG = "NewsFragment";
    private NewsPresenter mPresenter;
    private String mCategory;

    public static NewsFragment newInstance(String category) {
        Bundle agrs = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        agrs.putString("category" , category);
        newsFragment.setArguments(agrs);
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
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder( getActivity() , true);
        refreshViewHolder.setOriginalImage(R.mipmap.ic_nav_news_normal);
        refreshViewHolder.setUltimateColor(R.color.main_green);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    private void createP() {
        mPresenter = new NewsPresenter();
    }

    @Override
    protected void fetchData() {
        L.d(TAG,"fetchData");
        mPresenter.loadNewsData(Constant.LOAD_TYPE_NORMAL , mCategory);
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

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        return false;
    }
}
