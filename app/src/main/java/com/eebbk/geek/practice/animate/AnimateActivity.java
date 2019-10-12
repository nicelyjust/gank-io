package com.eebbk.geek.practice.animate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.practice.animate
 *  @文件名:   AnimateActivity
 *  @创建者:   lz
 *  @创建时间:  2019/10/9 18:09
 *  @描述：XML也可以设置 <animator>  对应代码中的ValueAnimator
 *                       <objectAnimator>  对应代码中的ObjectAnimator
 *                       <set>  对应代码中的AnimatorSet
 */
public class AnimateActivity extends BaseActivity {
    @BindView(R.id.btn_animate)
    Button mBtnAnimate;
    @BindView(R.id.tv_animate)
    TextView mTvAnimate;
    @BindView(R.id.av_anim)
    AnimView mAnimView;

    @Override
    protected int getContentView() {
        return R.layout.activity_animate;
    }

    @Override
    protected void initWidget() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(Arrays.asList(ObjectAnimator.ofFloat(mTvAnimate, "translationX", -500f, 0f),
                ObjectAnimator.ofFloat(mTvAnimate, "alpha", 0, 1)));
        animatorSet.setDuration(3000);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(AnimateActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Toast.makeText(AnimateActivity.this, "onAnimationStart", Toast.LENGTH_SHORT).show();
            }
        });
        animatorSet.start();
    }

    @OnClick({R.id.btn_animate,R.id.btn_start_anim, R.id.btn_pause_anim, R.id.btn_stop_anim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_animate:
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator translationY = ObjectAnimator.ofFloat(mTvAnimate, "translationY", -100, 80, 50, 30, 20, 0, 10, 5, 0);
                animatorSet.play(ObjectAnimator.ofFloat(mTvAnimate, "rotation", 0, 360))
                        .after(translationY);
                animatorSet.setDuration(5000);
                animatorSet.start();

                break;
            case R.id.btn_start_anim:
                mAnimView.startAnim();
                break;
            case R.id.btn_pause_anim:
                mAnimView.pauseAnimation();
                break;
            case R.id.btn_stop_anim:
                mAnimView.stopAnimation();
                break;
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimateActivity.class);
        context.startActivity(starter);
    }
}
