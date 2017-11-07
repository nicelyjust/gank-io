package com.eebbk.nicely.demo.ui.lazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   DemoFragment
 *  @创建者:   lz
 *  @创建时间:  2017/11/1 11:52
 *  @修改时间:  Administrator 2017/11/1 11:52 
 *  @描述：    TODO
 */
public class DemoFragment extends Fragment {
    private static final String TAG      = "DemoFragment";
    private static final String POSITION = "position";
    @BindView(R.id.tv_fragment_name)
    TextView mTvName;
    private String mPosition;
    private Unbinder mBind;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        mBind = ButterKnife.bind(this, view);
        L.d("lz", mPosition + ":创建了");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.d("lz", mPosition + ":view创建完毕");
        isViewCreated = true;
        lazyLoad();
    }

    private void lazyLoad() {
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    private void loadData() {
        L.d("lz" , mPosition + ": 加载数据了");
        mTvName.setText("fragment#:" + mPosition);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        L.d(TAG,  " isVisibleToUser == " + isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("lz", mPosition + ":销毁了");
        mBind.unbind();
    }
}
