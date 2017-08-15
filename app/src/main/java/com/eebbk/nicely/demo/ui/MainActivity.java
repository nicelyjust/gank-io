package com.eebbk.nicely.demo.ui;

import android.view.View;
import android.widget.Button;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo
 *  @文件名:   MainActivity
 *  @创建者:   lz
 *  @创建时间:  2017/7/19 18:07
 *  @描述：    TODO
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.add)
    Button mAdd;
    private 生成 m生成;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        mAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if (m生成 == null) {
                    m生成 = new 生成(1280, 720, "");
                    m生成.generate();
                }
                break;
            default:
                 break;
        }
    }
}
