package com.eebbk.geek.home.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.eebbk.geek.base.fragment.LazyBaseFragment2;
import com.eebbk.geek.R;

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

public class NewsFragment extends LazyBaseFragment2
        implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.rv_news)
    RecyclerView     mRv;
    @BindView(R.id.refresh_layout_news)
    BGARefreshLayout mRefreshLayout;

    public static NewsFragment newInstance() {
         NewsFragment fragment = new NewsFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initWidget(View root) {
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder refreshViewHolder = new BGAMoocStyleRefreshViewHolder( getActivity() , true);
        refreshViewHolder.setOriginalImage(R.mipmap.ic_nav_news_normal);
        refreshViewHolder.setUltimateColor(R.color.main_green);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    protected void fetchData() {
        /*// 延迟加载
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        return false;
    }
}
