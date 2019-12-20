package com.eebbk.geek.module.viewLearn;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.BaseFragment;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.viewLearn
 *  @文件名:   SlideFragment
 *  @创建者:   lz
 *  @创建时间:  2019/12/18 19:36
 *  @描述：
 */
public class XferModeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_x_mode;
    }

    public static XferModeFragment newInstance() {
        XferModeFragment fragment = new XferModeFragment();
        return fragment;
    }
}
