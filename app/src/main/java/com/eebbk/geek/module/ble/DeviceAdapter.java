package com.eebbk.geek.module.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.eebbk.geek.base.BaseRvAdapter;


/*
 *  @项目名：  mock500
 *  @包名：    com.yunzhou.tdinformation.etc
 *  @文件名:   SimpleAdapter
 *  @创建者:   lz
 *  @创建时间:  2019/1/29 15:01
 *  @描述：
 */
public class DeviceAdapter extends BaseRvAdapter<BluetoothDevice> {

    private OnItemClickListener mListener;

    public DeviceAdapter(Context context, OnItemClickListener listener) {
        super(context);
        mListener = listener;
    }

    @Override
    protected RecyclerView.ViewHolder createHolderView(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void bindHolderView(RecyclerView.ViewHolder holder, BluetoothDevice bluetoothDevice, int position) {
    }

    public BluetoothDevice getItem(int pos) {
        return null;
    }

    interface OnItemClickListener {
        void onItemClick(int pos);
    }

}
