package com.eebbk.geek.stickyheaders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eebbk.videoteam.utils.account.AccountManager;
import com.eebbk.vtraining.R;
import com.eebbk.vtraining.credit.beans.CreditDetailViewHolder;
import com.eebbk.vtraining.credit.beans.IntegrationVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chow on 2016/12/26.
 */

public class CreditDetailAdapter extends RecyclerView.Adapter<CreditDetailViewHolder>{
    private Context mContext;
    private ICreditAdapterPresenter mICreditAdapterPresenter;
    private ICreditDetailAdapterListener mICreditDetailAdapterListener;
    private List<IntegrationVo> creditData = new ArrayList<>();

    public CreditDetailAdapter(Context context, ICreditDetailAdapterListener iCreditDetailAdapterListener) {
        mContext = context;
        mICreditDetailAdapterListener = iCreditDetailAdapterListener;
        mICreditAdapterPresenter = new CreditAdapterPresenterImpl(context);
        setHasStableIds(true);//使用addItemDecoration必须设置为true
    }

    @Override
    public int getItemCount() {
        return creditData.size();
    }

    @Override
    public long getItemId( int position ) {
        //使用addItemDecoration必须复写此方法
        return position;
    }

    @Override
    public CreditDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_credit_detail, parent, false);
        return new CreditDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreditDetailViewHolder holder, int position) {
        bind(holder,position);
    }

    public ICreditAdapterPresenter getICreditAdapterPresenter() {
        return mICreditAdapterPresenter;
    }

    public void setDatas(List<IntegrationVo> resultData){
        creditData = resultData;
        notifyDataSetChanged();
    }

    public List<IntegrationVo> getDatas(){
        return creditData;
    }

    public void bind(final CreditDetailViewHolder holder, final int position){
        IntegrationVo mIntegration = creditData.get(position);
        String scores = String.valueOf(mIntegration.getScore());
        int isReceived = mIntegration.getIsReceived();
        holder.getCreditName().setText(mIntegration.getRule());
        holder.getCreditNumber().setText("+" + scores);
//        holder.getCreditNumber().setVisibility( isReceived== 0?View.VISIBLE : View.INVISIBLE);
        holder.getCreditBtn().setText("领取+" + scores);
       // holder.getCreditBtn().setVisibility(isReceived == 1?View.VISIBLE : View.INVISIBLE);
        holder.getCreditBtn().setVisibility((AccountManager.getInstance().isUserLogin() && isReceived == 1)? View.VISIBLE : View.INVISIBLE);
//        holder.getCreditLayout().setVisibility(isReceived == 0?View.VISIBLE : View.INVISIBLE);

        holder.getCreditBtn().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mICreditDetailAdapterListener.DetailAdapterItemClick(v,position);
                v.setVisibility(View.INVISIBLE);
                holder.getCreditNumber().setVisibility(View.VISIBLE);
                creditData.get(position).setIsReceived(0);
            }
        });
    }
}
