package com.eebbk.nicely.demo.ui.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.fragment.LazyBaseFragment2;
import com.eebbk.nicely.demo.utils.L;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   DemoFragment
 *  @创建者:   lz
 *  @创建时间:  2017/11/1 11:52
 *  @修改时间:  Administrator 2017/11/1 11:52 
 *  @描述：    TODO
 */
public class DemoFragment extends LazyBaseFragment2 {
    private static final String TAG      = "DemoFragment";
    private static final String POSITION = "position";
    @BindView(R.id.tv_fragment_name)
    TextView mTvName;
    private String mPosition;

    public static DemoFragment newInstance(String str) {
        Bundle args = new Bundle();
        args.putString(POSITION, str);
        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getString(POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d("lz", mPosition + ":创建了");
        return super.onCreateView( inflater, container,savedInstanceState);
    }

    @Override
    protected void fetchData() {
        L.d("lz" , mPosition + ": 加载数据了");
        mTvName.setText("fragment#:" + mPosition);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        L.d("lz",  " isVisibleToUser == " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("lz", mPosition + ":销毁了");
    }
}
