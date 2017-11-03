package com.eebbk.nicely.demo.ui;

import android.widget.Toast;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;
import com.eebbk.nicely.demo.view.SpiderView;
import com.eebbk.nicely.demo.view.TapeView;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   DemoActivity
 *  @创建者:   lz
 *  @创建时间:  2017/9/20 19:55
 *  @修改时间:  Administrator 2017/9/20 19:55 
 *  @描述：
 */
public class DemoActivity extends BaseActivity implements TapeView.OnValueChangeListener {
    /*private static final String TAG = "DemoActivity";
    @BindView(R.id.tape_view)
    TapeView mTapeView;
    @BindView(R.id.ruler_view)
//    RulerView mRuler;
    Ruler    mRuler;*/
    /*@BindView(R.id.pie_view)
    PieView mPieView;
    private List<PieVo> mPieVos = new ArrayList<>(6);*/
    @BindView(R.id.spider_view)
    SpiderView mTapeView;
    @Override
    protected int getContentView() {

        return R.layout.activity_demo;
    }

    @Override
    protected void initData() {
       /* PieVo pieVo = new PieVo(200 , 0.20f , "嘿嘿" );
        pieVo.setColor(Color.parseColor("#E25B5A"));
        PieVo pieVo1 = new PieVo(200 , 0.40f , "哈哈" );
        pieVo1.setColor(Color.parseColor("#24cf5f"));
        PieVo pieVo2 = new PieVo(200 , 0.40f , "嘻嘻" );
        pieVo2.setColor(Color.BLUE);
        mPieVos.add(pieVo);
        mPieVos.add(pieVo1);
        mPieVos.add(pieVo2);
        mPieView.setPieList(mPieVos);*/
//        mTapeView.setOnValueChangeListener(this);
    }

    @Override
    public void onChange(float value) {
        Toast.makeText(this , "current value == " + value , Toast.LENGTH_SHORT).show();
    }
}
