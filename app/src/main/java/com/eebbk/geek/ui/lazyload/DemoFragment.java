package com.eebbk.geek.ui.lazyload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.news.CourseBean;
import com.eebbk.geek.rxLearn.RxJavaActivity;
import com.eebbk.geek.utils.L;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
public class DemoFragment extends LazyBaseFragment implements View.OnClickListener {
    private static final String TAG      = "DemoFragment";
    private static final String POSITION = "position";
    private String[] subjectRes = {"物理" ,"数学" , "语文"  , "政治" , "生物" ,  "化学" , "历史" , "地理" ,"英语" };
    @BindView(R.id.tv_fragment_name)
    TextView mTvName;
    @BindView(R.id.rv_demo)
    RecyclerView mRv;
    private String mPosition;
    private List<CourseBean> mCourseBeanlist ;
    private Random mRandom;

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


    @Override
    protected void fetchData() {
        L.d("fetchData" , mPosition + ": 加载数据了");
        mTvName.setText("fragment#:" + mPosition);
        mockData();
        Collections.sort(mCourseBeanlist);
    }

    private void mockData() {
        for (int i = 1; i <= 18; i++) {
            CourseBean courseBean = new CourseBean("cp" + i % 9, i % 9);
            courseBean.setSubjectName(subjectRes[i % 9]);
            courseBean.setSubjectID(i % 9);
            mCourseBeanlist.add(courseBean);
        }
        CourseBean courseBean = new CourseBean("cp" + 9, 9);
        int i = mRandom.nextInt(10);
        courseBean.setSubjectName(subjectRes[i]);
        courseBean.setSubjectID(i);

        CourseBean courseBean1 = new CourseBean("cp" + 10, 10);
        int i1 = mRandom.nextInt(10);
        courseBean1.setSubjectName(subjectRes[i1]);
        courseBean1.setSubjectID(i1);

        CourseBean courseBean2 = new CourseBean("cp" + 11, 11);
        int i2 = mRandom.nextInt(10);
        courseBean2.setSubjectName(subjectRes[i2]);
        courseBean2.setSubjectID(i2);

        CourseBean courseBean3 = new CourseBean("cp" + 12, 12);
        int i3 = mRandom.nextInt(10);
        courseBean3.setSubjectName(subjectRes[i3]);
        courseBean3.setSubjectID(i3);

        CourseBean courseBean4 = new CourseBean("cp" + 13, 13);
        int i4 = mRandom.nextInt(10);
        courseBean4.setSubjectName(subjectRes[i4]);
        courseBean4.setSubjectID(i4);

        mCourseBeanlist.add(courseBean);
        mCourseBeanlist.add(courseBean1);
        mCourseBeanlist.add(courseBean2);
        mCourseBeanlist.add(courseBean3);
        mCourseBeanlist.add(courseBean4);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initWidget(View root) {
        mRandom = new Random();
        mTvName.setOnClickListener(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.Extra.DEMO_FOR_RESULT:
                    String stringExtra = data.getStringExtra(Constant.Extra.DEMO_POSITION);
                    mTvName.setText("i got you" + stringExtra );
                    break;
                default:
                     break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("DemoFragment", mPosition + ":销毁了");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fragment_name:
                Intent intent = new Intent(mContext ,RxJavaActivity.class);
                intent.putExtra(Constant.Extra.DEMO_POSITION , mPosition);
                startActivityForResult(intent , Constant.Extra.DEMO_FOR_RESULT);
                 break;
            default:
                 break;
        }
    }
}
