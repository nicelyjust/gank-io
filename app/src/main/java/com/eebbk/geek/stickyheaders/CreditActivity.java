package com.eebbk.geek.stickyheaders;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eebbk.videoteam.NetWorkService.NetWorkRequest;
import com.eebbk.videoteam.utils.L;
import com.eebbk.videoteam.utils.NetWorkUtils;
import com.eebbk.videoteam.utils.T;
import com.eebbk.videoteam.utils.UserPreferences;
import com.eebbk.vtraining.R;
import com.eebbk.vtraining.app.AppConst;
import com.eebbk.vtraining.base.BaseActivity;
import com.eebbk.vtraining.credit.adapter.ICreditDetailAdapterListener;
import com.eebbk.vtraining.credit.beans.CreditContant;
import com.eebbk.vtraining.credit.beans.IntegrationRule;
import com.eebbk.vtraining.credit.beans.IntegrationVo;
import com.eebbk.vtraining.credit.presenter.CreditPresenterImpl;
import com.eebbk.vtraining.credit.presenter.ICreditListener;
import com.eebbk.vtraining.credit.presenter.ICreditPresenter;
import com.eebbk.vtraining.credit.util.CreditUserLoginUtil;
import com.eebbk.vtraining.creditmarket.view.CreditMarketActivity;
import com.eebbk.vtraining.dacollect.bean.CreditInfoDA;
import com.eebbk.vtraining.enterAccount.enterActivity.beans.EnterAccountConstant;
import com.eebbk.vtraining.enterAccount.enterActivity.view.EnterAccountActivity;
import com.eebbk.vtraining.util.ParabolaAnimHelper;
import com.eebbk.vtraining.util.StatusBarUtil;
import com.eebbk.vtraining.view.LoadingAnim;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudezu on 2016/12/23.
 */

public class CreditActivity extends BaseActivity implements ICreditListener, View.OnClickListener, ICreditDetailAdapterListener{
    private Button mCreditBack;
    private Button mCreditBack1;
    private RiseNumberTextView mCreditNumber;
    private TextView mCreditStore;
    private PullToRefreshRecyclerView mCreditRecycleView;
    private LoadingAnim mLoadingView;
    private RelativeLayout mNoDataTips;
    private RelativeLayout mNoNetTips;
    private Button mNoNetBtn;
    private TextView mTipsText;
    private RelativeLayout mCreditLayout;
    private RelativeLayout mEnterLayout;
    private Button mEnterBtn;
    private TextView mCreditStore1;
    private TextView mTiaozhuantisi;
    private ICreditPresenter mICreditPresenter;
    private CreditDetailAdapter mCreditDetailAdapter;
    private CreditHeadAdapter mCreditHeadAdapter;
    private StickyHeadersItemDecoration mStickyHeadersItemDecoration;
    private List<IntegrationVo> mCreditData = new ArrayList<>();
    private int statusBarHeight;
    private ParabolaAnimHelper parabolaAnimHelper;
    private ImageView myCreditImageView;
    private int mCreditCount = 0;
    private boolean mIsGeting = false;
    private int mAddScore = 0;
    private View mScoreCoin;
    private IntentFilter mIntentFilter;
    private EnterSuccessReceiver mEnterSuccessReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        initView();
        initPresenter();
        initRejesterReceiver();
    }

    private void getCreditCountFromShared(){
        mCreditCount = UserPreferences.loadInt(this, CreditContant.CREDIT_COUNT_KEY, 0);
    }

    private void setCreditCountToShared(int creditcount){
        UserPreferences.saveInt(this,CreditContant.CREDIT_COUNT_KEY,creditcount);
    }

    private void initRejesterReceiver(){
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(EnterAccountConstant.ENTER_SUCCESS_BROASTCASE);
        mEnterSuccessReceiver = new EnterSuccessReceiver();
        registerReceiver(mEnterSuccessReceiver,mIntentFilter);
    }
    public class EnterSuccessReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(EnterAccountConstant.ENTER_SUCCESS_BROASTCASE)){
                showEnterBtn(true);
                setViewIsLoading(true);
                showDataEmpty(false);
                showNetError(false);
                mICreditPresenter.getCreditListData();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mEnterSuccessReceiver);
        NetWorkRequest.getInstance(this).cancleRequest(getClass().getName());
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        requestCreditListData();
    }

    @Override
    public void onNetWorkConnectChanged(boolean isWifiOn, boolean isMobileOn) {
        super.onNetWorkConnectChanged(isWifiOn, isMobileOn);
        if(isNetWorkConnect() ){
            //requestCreditListData();
        }
    }

    private void initView(){
        mCreditLayout = (RelativeLayout)findViewById(R.id.activity_credit_upsection_id);
        mCreditBack = (Button)findViewById(R.id.credit_back);
        mCreditBack.setOnClickListener(this);
        mCreditBack1 = (Button)findViewById(R.id.credit_back1);
        mCreditBack1.setOnClickListener(this);
        mCreditNumber = (RiseNumberTextView)findViewById(R.id.credit_number);
        getCreditCountFromShared();
        mCreditNumber.setText(new DecimalFormat("###,###,###,###,###").format(mCreditCount));
        mCreditStore = (TextView)findViewById(R.id.credit_store);
        mCreditStore.setOnClickListener(this);
        mEnterLayout = (RelativeLayout)findViewById(R.id.activity_credit_enter_section);
        mCreditStore1 = (TextView)findViewById(R.id.credit_store_1);
        mCreditStore1.setOnClickListener(this);
        mEnterBtn = (Button)findViewById(R.id.activity_credit_enter_btn);
        mEnterBtn.setOnClickListener(this);
        mTiaozhuantisi = (TextView)findViewById(R.id.activity_credit_tiaozhang_tishi);
        if(CreditUserLoginUtil.getmCreditUserLoginUtlInstance().isPersonalApk(this)){
            mTiaozhuantisi.setVisibility(View.VISIBLE);
        }else {
            mTiaozhuantisi.setVisibility(View.GONE);
        }
        myCreditImageView = (ImageView) findViewById(R.id.credit_coin_imageView);
        mCreditRecycleView = (PullToRefreshRecyclerView)findViewById(R.id.credit_recycle_view);
        initRecycleView();
        parabolaAnimHelper = new ParabolaAnimHelper();
        initParabolaAnim();
        mLoadingView = (LoadingAnim) findViewById(R.id.top_teacher_loading_view);
        mNoDataTips = (RelativeLayout) findViewById(R.id.no_data_tips);
        mTipsText = (TextView) findViewById(R.id.none_content_tips_main);
        mNoNetTips = (RelativeLayout) findViewById(R.id.no_net_tips);
        mNoNetBtn = (Button) findViewById(R.id.click_refresh_id);
        mNoNetBtn.setOnClickListener(this);
        mNoDataTips.setOnClickListener(this);
    }
    @Override
    public void showEnterBtn(boolean isShow){
        if (!isShow){
            mCreditLayout.setVisibility(View.INVISIBLE);
            mEnterLayout.setVisibility(View.VISIBLE);
        }else {
            mCreditLayout.setVisibility(View.VISIBLE);
            mEnterLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void initRecycleView(){
        mCreditRecycleView.getRefreshableView().setLayoutManager(new LinearLayoutManager(mCreditRecycleView.getContext()));
        mCreditRecycleView.setScrollingWhileRefreshingEnabled(true);
        mCreditRecycleView.setHideHeaderEver(true);
        mCreditRecycleView.setHideFooterEver(true);

        mCreditDetailAdapter = new CreditDetailAdapter(this, this);
        mCreditHeadAdapter = new CreditHeadAdapter(this, mCreditDetailAdapter.getICreditAdapterPresenter());
        mStickyHeadersItemDecoration = new StickyHeadersBuilder()
                .setAdapter(mCreditDetailAdapter)
                .setRecyclerView(mCreditRecycleView.getRefreshableView())
                .setOnHeaderClickListener(new OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, long headerId) {
                        Log.d("liudezu","============onHeaderClick:"+headerId);
                    }
                })
                .setStickyHeadersAdapter(mCreditHeadAdapter)
                .build();
        mCreditRecycleView.getRefreshableView().setAdapter(mCreditDetailAdapter);
        mCreditRecycleView.getRefreshableView().addItemDecoration(mStickyHeadersItemDecoration);
        mCreditRecycleView.getRefreshableView().setItemAnimator(null);
        mCreditRecycleView.setVisibility(View.INVISIBLE);
    }

    private void initPresenter(){
        mICreditPresenter = new CreditPresenterImpl(this, this);
    }

    //请求领取积分列表数据
    private void requestCreditListData(){
        if(!mICreditPresenter.isDataEmpty() && !CreditUserLoginUtil.getmCreditUserLoginUtlInstance().isUserLogin()){
            return;
        }
        if (!isNetWorkConnect()) {
            setViewIsLoading(false);
            showNetError(true);
            return;
        }
        setViewIsLoading(true);
        showDataEmpty(false);
        showNetError(false);
        mICreditPresenter.getCreditListData();
    }

    @Override
    public void onGetCreditListDataSuccess(IntegrationRule resultData){
        setViewIsLoading(false);
        showNetError(false);
        showDataEmpty(false);

        showEnterBtn( CreditUserLoginUtil.getmCreditUserLoginUtlInstance().isUserLogin());
        mCreditRecycleView.setVisibility(View.VISIBLE);
        initListDataAndAdapter(resultData.getRules());

        mCreditCount = resultData.getTotalScore();
        loadTotalUpCreditNumber(mCreditCount);
    }

    @Override
    public void onGetCreditListDataEmpty(){
        setViewIsLoading(false);
        showDataEmpty(true);
        showNetError(false);
    }

    @Override
    public void onGetCreditListDataFailed(){
        setViewIsLoading(false);
        showDataEmpty(false);
        showNetError(true);

        showEnterBtn( CreditUserLoginUtil.getmCreditUserLoginUtlInstance().isUserLogin());
    }

    @Override
    public void onGetCreditTotalDataSuccess(){
    }

    @Override
    public void onGetCreditTotalDataEmpty(){

    }

    @Override
    public void onGetCreditTotalDataFailed(){

    }

    //领取积分
    public void addCreditData(String integrationId,int scores){
        Log.d("liudezu","============领取积分");
        mICreditPresenter.addCreditData(integrationId,scores);
    }

    @Override
    public void onAddCreditDataSuccess(){
        T.getInstance(this).s("积分领取成功");
        Log.d("liudezu","============领取积分-成功");
        upCreditNumber(mAddScore);
        parabolaAnimHelper.playParabolaAnimCredit(myCreditImageView, mScoreCoin, statusBarHeight);
        CreditInfoDA.clickGetCredit(this);
    }

    @Override
    public void onAddCreditDataFailed(){
        T.getInstance(this).s("积分领取失败，请稍后试试！");
    }

    public void initListDataAndAdapter(List<IntegrationVo> resultData){
        mCreditData = resultData;
        mCreditDetailAdapter.setDatas(resultData);
        mCreditHeadAdapter.setDatas(resultData);
    }

    @Override
    public void DetailAdapterItemClick(View starView,int pos){
        Log.d("liudezu","======click:"+pos);
        if (mIsGeting) {
            return;
        }
        mIsGeting = true;
        if(null != mCreditData && !mCreditData.isEmpty() && mCreditData.size()> pos){
            addCreditData(mCreditData.get(pos).getIntegrationId(),mCreditData.get(pos).getScore());
            mAddScore = mCreditData.get(pos).getScore();
            mScoreCoin = starView;
        }

    }
    public void setViewIsLoading(boolean loading) {
        if (loading && mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        } else if(mLoadingView != null){
            mLoadingView.setVisibility(View.INVISIBLE);
        }
    }

    private void showNetError(boolean show){
        if (isNetWorkConnect()) {
            mTipsText.setText(getString(R.string.net_load_error_tip));
            mNoNetBtn.setBackgroundResource(R.drawable.click_me_refresh_btn_selector);
        } else {
            mTipsText.setText(getString(R.string.net_wifi_error_tip));
            mNoNetBtn.setBackgroundResource(R.drawable.click_me_linknet_btn_selector);
        }
        mNoNetTips.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showDataEmpty(boolean show){
        mNoDataTips.setVisibility(show ? View.VISIBLE:View.GONE);
    }

    /**
     * 初始化金币抛出动画
     */
    private void initParabolaAnim() {
        parabolaAnimHelper.setDuration(600);
        statusBarHeight = StatusBarUtil.getRealStatusHeight(this);
        parabolaAnimHelper.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                myCreditImageView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void upCreditNumber(final int addScore) {
        mCreditNumber.withNumber(mCreditCount, mCreditCount + addScore);
        mCreditNumber.setDuration(1000);
        mCreditNumber.start();
        mCreditNumber.setOnEnd(new RiseNumberTextView.EndListener() {
            @Override
            public void onEndFinish() {
                mCreditCount = mCreditCount + addScore;
                mCreditNumber.setText(new DecimalFormat("###,###,###,###,###").format(mCreditCount));
                L.d("lmlm", "添加后总积分 = " + mCreditCount);
                mIsGeting = false;
                setCreditCountToShared(mCreditCount);
            }
        });
    }

    /**
     * 加载总积分 动效
     *
     * @param totalScore
     */
    private void loadTotalUpCreditNumber(final int totalScore) {
        mCreditNumber.withNumber(0, totalScore);
        mCreditNumber.setDuration(1000);
        mCreditNumber.start();
        mCreditNumber.setOnEnd(new RiseNumberTextView.EndListener() {

            @Override
            public void onEndFinish() {
                setCreditCountToShared(mCreditCount);
                mCreditNumber.setText(new DecimalFormat("###,###,###,###,###").format(mCreditCount));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.credit_back:
            case R.id.credit_back1:
                clickCreditBack();
                break;
            case R.id.credit_store:
            case R.id.credit_store_1://积分商城
                clickCreditStore();
                break;
            case R.id.click_refresh_id:
                if (isNetWorkConnect()) {
                    requestCreditListData();
                }else{
                    NetWorkUtils.startWifiSetting(this);
                }
                break;
            case R.id.no_data_tips:
                requestCreditListData();
                break;
            case R.id.activity_credit_enter_btn://进入登入界面
                clickEnter();
                break;
            default:
                break;
        }
    }

    private void clickEnter(){
        if(CreditUserLoginUtil.getmCreditUserLoginUtlInstance().isPersonalApk(this)){
            CreditUserLoginUtil.getmCreditUserLoginUtlInstance().goToPersonalLogin(this);
        }else {
            Intent intent = new Intent(this, EnterAccountActivity.class);
            startActivity(intent);
        }
        CreditInfoDA.clickEnterCredit(this);
    }

    private void clickCreditBack(){
        Intent data = new Intent();
        data.putExtra(AppConst.IntentConst.INTENT_CREDIT_COUNT, mCreditCount);
        setResult(RESULT_OK, data);
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    private void clickCreditStore() {
        Intent intent = new Intent(this, CreditMarketActivity.class);
        startActivity(intent);
        CreditInfoDA.clickCreditMarket(this);
    }

    @Override
    public void onBackPressed(){
        clickCreditBack();
    }


}
