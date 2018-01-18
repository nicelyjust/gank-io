package com.eebbk.geek.mine;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.mine
 *  @创建者:   lz
 *  @创建时间:  2018/1/14 15:15
 *  @修改时间:  nicely 2018/1/14 15:15
 *  @描述：    TODO
 */

public class MineFragment extends LazyBaseFragment {
    @Override
    protected void fetchData() {
        Window window = getActivity().getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.parseColor("#10000000"));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onBindViewBefore(View root) {

    }

    public static MineFragment newInstance() {
        return new MineFragment();
    }
}
