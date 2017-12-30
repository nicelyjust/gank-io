package com.eebbk.geek.stickyheaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eebbk.vtraining.R;
import com.eebbk.vtraining.credit.beans.CreditHeadViewHolder;
import com.eebbk.vtraining.credit.beans.IntegrationVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chow on 2016/12/26.
 */

public class CreditHeadAdapter implements StickyHeadersAdapter<CreditHeadViewHolder>{
    private Context mContext;
    private ICreditAdapterPresenter mICreditAdapterPresenter;
    private List<IntegrationVo> creditData = new ArrayList<>();

    public CreditHeadAdapter(Context context, ICreditAdapterPresenter ICreditAdapterPresenter) {
        mContext = context;
        mICreditAdapterPresenter = ICreditAdapterPresenter;
    }

    @Override
    public long getHeaderId(int position) {
        return creditData.get( position ).getType();
    }


    @Override
    public CreditHeadViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_credit_head, parent, false);
        return new CreditHeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreditHeadViewHolder holder, int position) {
        bind(holder,position);
    }

    public void setDatas(List<IntegrationVo> resultData){
        creditData = resultData;
    }

    public void bind(CreditHeadViewHolder holder, int position){
        IntegrationVo mIntegration = creditData.get(position);
        holder.getHeadName().setText(mIntegration.getTypeName());
    }
}
