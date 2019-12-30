package com.eebbk.geek.module.mine;

import android.view.View;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;

import butterknife.BindView;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.mine
 *  @创建者:   lz
 *  @创建时间:  2018/1/14 15:15
 *  @修改时间:  nicely 2018/1/14 15:15
 *  @描述：
 */

public class MineFragment extends LazyBaseFragment implements View.OnClickListener {
    @BindView(R.id.fi_bluetooth)
    FuncItem mFiBluetooth;

    @Override
    protected void initWidget(View root) {
        mFiBluetooth.setOnClickListener(this);
    }

    @Override
    protected void fetchData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }


    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fi_bluetooth) {

        }
    }
}
