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
import com.eebbk.geek.utils.L;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.base.fragment
 *  @文件名:   BaseFragment
 *  @创建者:   lz
 *  @创建时间:  2017/9/26 19:44
 *  @修改时间:  Administrator 2017/9/26 19:44 
 *  @描述：针对hide show 的延迟加载
 */
public abstract class LazyBaseFragment extends Fragment {
    private static final String TAG = "LazyBaseFragment";
    public Context mContext;
    private Bundle mBundle;
    private View mRoot;
    private Unbinder mBind;
    protected  boolean  isFirstLoad = true;
    protected boolean isVisible = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        L.d(TAG, " onAttach()" );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        L.d(TAG, " onDetach()" );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
        L.d(TAG, " onCreate()" );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null)
                parent.removeView(mRoot);
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false);
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
        L.d(TAG, " onCreateView()" );
        return mRoot;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        L.d(TAG, " setUserVisibleHint: " + isVisibleToUser);
        this.isVisible = isVisibleToUser;
        if(getUserVisibleHint()) {
            onVisible();
        }
    }
    /**
     * 可见, 执行延迟加载
     */
    protected void onVisible() {
        if(isFirstLoad && getView() != null){
            lazyLoad();
            isFirstLoad = false;
        }
    }

    protected abstract void lazyLoad();


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
    public void onResume() {
        super.onResume();
        if(getUserVisibleHint()){
            onVisible();
        }
        L.d(TAG, " onResume()" );
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
