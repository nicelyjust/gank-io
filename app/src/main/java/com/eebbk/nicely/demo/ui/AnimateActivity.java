package com.eebbk.nicely.demo.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;
import com.eebbk.nicely.demo.utils.L;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   AnimateActivity
 *  @创建者:   lz
 *  @创建时间:  2017/9/21 16:44
 *  @修改时间:  Administrator 2017/9/21 16:44 
 *  @描述：    动画研究
 */
public class AnimateActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "AnimateActivity";
    @BindView(R.id.ll_login_layer)
    View         mLlLoginLayer;
    @BindView(R.id.ib_login_weibo)
    ImageView    mIbLoginWeibo;
    @BindView(R.id.ib_login_wx)
    ImageView    mIbLoginWx;
    @BindView(R.id.ib_login_qq)
    ImageView    mIbLoginQq;
    @BindView(R.id.ll_login_options)
    LinearLayout mLlLoginOptions;
    @BindView(R.id.ll_login_pull)
    LinearLayout mLlLoginPull;

    @Override
    protected int getContentView() {
        return R.layout.activity_main_login;
    }

    @Override
    protected void initWidget() {
        mLlLoginPull.setOnClickListener(this);
        mLlLoginLayer.setOnClickListener(this);
        mIbLoginQq.setOnClickListener(this);
        mIbLoginWx.setOnClickListener(this);
        mIbLoginWeibo.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login_layer:
            case R.id.ll_login_pull:

                mLlLoginPull.animate().cancel();
                mLlLoginLayer.animate().cancel();

                int height = mLlLoginOptions.getHeight();
                // 判断黑色背景view的tag,默认是1
                float progress = (mLlLoginLayer.getTag() != null && mLlLoginLayer.getTag() instanceof Float) ?
                                 (float) mLlLoginLayer.getTag() : 1;
                int time = (int) (360 * progress);

                if (mLlLoginPull.getTag() != null) {
                    mLlLoginPull.setTag(null);
                    glide(height , time);
                } else {
                    mLlLoginPull.setTag(true);
                    upGlide(time);
                }
                break;
            default:
                break;
        }
    }
    private void upGlide(int time) {

        mLlLoginPull.animate()
//                    .translationYBy(height * progress)
                    .translationY(0)
                    .setDuration(time)
                    .start();
        mLlLoginLayer.animate()
//                     .alphaBy(1 - progress)
                     .alpha(1)
                     .setDuration(time)
                     .setListener(new AnimatorListenerAdapter() {
                         @Override
                         public void onAnimationStart(Animator animation) {
                             mLlLoginLayer.setVisibility(View.VISIBLE);
                         }

                         @Override
                         public void onAnimationCancel(Animator animation) {
                             if (animation instanceof ValueAnimator) {
                                 Object animatedValue = ((ValueAnimator) animation).getAnimatedValue();
                                 mLlLoginLayer.setTag(animatedValue);
                             }
                         }

                         @Override
                         public void onAnimationEnd(Animator animation) {
                             if (animation instanceof ValueAnimator) {
                                 mLlLoginLayer.setTag(((ValueAnimator) animation).getAnimatedValue());
                             }
                         }
                     })
                     .start();
    }

    /**
     * menu glide
     *
     * @param height   height
     * @param time     time
     */
    private void glide(int height, int time) {
        mLlLoginPull.animate()
//                    .translationYBy(height - height * progress)
                    .translationY(height)
                    .setDuration(time)
                    .start();

        mLlLoginLayer.animate()
//                     .alphaBy(1 * progress)
                     .alpha(0)
                     .setDuration(time)
                     .setListener(new AnimatorListenerAdapter() {

                         @Override
                         public void onAnimationCancel(Animator animation) {
                             if (animation instanceof ValueAnimator) {
                                 Object animatedValue = ((ValueAnimator) animation).getAnimatedValue();
                                 L.d("lz" , animatedValue instanceof Float ? animatedValue :"null");
                                 mLlLoginLayer.setTag(animatedValue);
                             }
                         }

                         @Override
                         public void onAnimationEnd(Animator animation) {
                             if (animation instanceof ValueAnimator) {
                                 Object animatedValue = ((ValueAnimator) animation).getAnimatedValue();
                                 L.d("lz" , animatedValue instanceof Float ? animatedValue :"null");
                                 mLlLoginLayer.setTag(animatedValue);
                             }
                             mLlLoginLayer.setVisibility(View.GONE);
                         }
                     })
                     .start();
    }
}
