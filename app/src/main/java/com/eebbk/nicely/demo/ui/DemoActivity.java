package com.eebbk.nicely.demo.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.activities.BaseActivity;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   DemoActivity
 *  @创建者:   lz
 *  @创建时间:  2017/9/20 19:55
 *  @修改时间:  Administrator 2017/9/20 19:55 
 *  @描述：    TODO
 */
public class DemoActivity extends BaseActivity {
    private static final String TAG = "DemoActivity";
    @BindView(R.id.rv_demo)
    RecyclerView mRv;

    private int[] imageRes = new int[]{R.mipmap.one ,R.mipmap.two ,R.mipmap.three ,R.mipmap.four, R.mipmap.one ,R.mipmap.two ,R.mipmap.three ,R.mipmap.four};
    private LayoutInflater mLayoutInflater;

    @Override
    protected int getContentView() {

        return R.layout.activity_demo;
    }

    @Override
    protected void initWidget() {
        mLayoutInflater = LayoutInflater.from(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL , false);
        mRv.setLayoutManager(linearLayoutManager);
        MyAdapter adapter = new MyAdapter();
        mRv.setAdapter(adapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = mLayoutInflater.inflate(R.layout.item_demo, parent, false);

            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imgView.setBackgroundResource(imageRes[position]);
        }

        @Override
        public int getItemCount() {
            return imageRes.length;
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.imgView = (ImageView) itemView.findViewById(R.id.iv_demo);
        }
    }
}
