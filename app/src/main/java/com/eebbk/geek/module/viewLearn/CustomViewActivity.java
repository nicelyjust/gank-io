package com.eebbk.geek.module.viewLearn;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentTransaction;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.btn_basic_knowledge)
    RadioButton mRbBasicKnowledge;
    @BindView(R.id.btn_basic_etc)
    RadioButton mRbBasicEtc;
    @BindView(R.id.btn_x_mode)
    RadioButton mRbXferMode;
    @BindView(R.id.btn_scroll)
    RadioButton mRbScroll;
    private PracticeFragment mPracticeFragment;
    private LiveFragment mLiveFragment;
    private SlideFragment mSlideFragment;
    private XferModeFragment mXferModeFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initWidget() {
        mPracticeFragment = PracticeFragment.newInstance();
        mLiveFragment = LiveFragment.newInstance();
        mXferModeFragment = XferModeFragment.newInstance();
        mSlideFragment = SlideFragment.newInstance();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_container, mPracticeFragment);
        ft.add(R.id.fl_container, mLiveFragment);
        ft.add(R.id.fl_container, mXferModeFragment);
        ft.add(R.id.fl_container, mSlideFragment);
        ft.hide(mSlideFragment);
        ft.hide(mXferModeFragment);
        ft.hide(mLiveFragment);
        ft.commitAllowingStateLoss();

        mRbBasicKnowledge.setChecked(true);
        mRbBasicEtc.setChecked(false);
        mRbScroll.setChecked(false);
    }

    @OnClick({R.id.btn_basic_knowledge, R.id.btn_basic_etc,R.id.btn_x_mode,R.id.btn_scroll})
    public void onViewClicked(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btn_basic_knowledge:
                ft.show(mPracticeFragment);
                ft.hide(mLiveFragment);
                ft.hide(mXferModeFragment);
                ft.hide(mSlideFragment);

                mRbBasicKnowledge.setChecked(true);
                mRbBasicEtc.setChecked(false);
                mRbXferMode.setChecked(false);
                mRbScroll.setChecked(false);
                break;
            case R.id.btn_basic_etc:
                ft.hide(mPracticeFragment);
                ft.show(mLiveFragment);
                ft.hide(mXferModeFragment);
                ft.hide(mSlideFragment);
                mRbBasicKnowledge.setChecked(false);
                mRbBasicEtc.setChecked(true);
                mRbXferMode.setChecked(false);
                mRbScroll.setChecked(false);
                break;
            case R.id.btn_x_mode:
                ft.hide(mPracticeFragment);
                ft.hide(mLiveFragment);
                ft.show(mXferModeFragment);
                ft.hide(mSlideFragment);
                mRbBasicKnowledge.setChecked(false);
                mRbBasicEtc.setChecked(false);
                mRbXferMode.setChecked(true);
                mRbScroll.setChecked(false);
                break;
            case R.id.btn_scroll:
                ft.hide(mPracticeFragment);
                ft.hide(mLiveFragment);
                ft.hide(mXferModeFragment);
                ft.show(mSlideFragment);
                mRbBasicKnowledge.setChecked(false);
                mRbBasicEtc.setChecked(false);
                mRbXferMode.setChecked(false);
                mRbScroll.setChecked(true);
                break;
        }
        ft.commitAllowingStateLoss();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewActivity.class);
        context.startActivity(starter);
    }

}
