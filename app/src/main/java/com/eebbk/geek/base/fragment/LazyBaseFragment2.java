package com.eebbk.geek.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.base.fragment
 *  @文件名:   BaseFragment
 *  @创建者:   lz
 *  @创建时间:  2017/9/26 19:44
 *  @修改时间:  Administrator 2017/9/26 19:44 
 *  @描述：
 */
public abstract class LazyBaseFragment2
        extends Fragment {
    private static final String TAG = "BaseFragment";
    public Context mContext;
    private Bundle mBundle;
    private View mRoot;
    private Unbinder mBind;
    private LayoutInflater mInflater;
    private boolean isViewInitiated;
    private boolean isVisibleToUser;
    private boolean isDataInitiated;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
            mInflater = inflater;
            // Do something
            onBindViewBefore(mRoot);
            // Bind view
            mBind = ButterKnife.bind(this, mRoot);
            // Get savedInstanceState
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            // Init
            initWidget(mRoot);
        }
        return mRoot;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    /**
     * 懒加载方法
     */
    protected abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    protected void onBindViewBefore(View root) {
        // ...
    }



    protected abstract int getLayoutId();

    protected void initBundle(Bundle bundle) {

    }
    protected RequestManager getImgLoader(){
        return Glide.with(this);
    }

    protected void initWidget(View root) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
        mBundle = null;
    }

    protected void onRestartInstance(Bundle savedInstanceState) {

    }
}
