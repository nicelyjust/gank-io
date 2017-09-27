package com.eebbk.nicely.demo.media;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;
import com.eebbk.nicely.demo.media.config.SelectOptions;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.media
 *  @文件名:   SelectImgActivity
 *  @创建者:   lz
 *  @创建时间:  2017/9/26 11:20
 *  @修改时间:  Administrator 2017/9/26 11:20 
 *  @描述：    TODO
 */
public class SelectImgActivity extends BaseActivity {
    private static final String TAG = "SelectImgActivity";
    private static SelectOptions mOption;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    public static void show(Context context, SelectOptions options) {
        mOption = options;
        context.startActivity(new Intent(context, SelectImgActivity.class));
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_select_img;
    }

    @Override
    protected void initWidget() {
        handleView();
    }

    /**
     * 填充Fragment
     */
    private void handleView() {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, SelectImgFragment.newInstance(mOption))
                    .commitNowAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
