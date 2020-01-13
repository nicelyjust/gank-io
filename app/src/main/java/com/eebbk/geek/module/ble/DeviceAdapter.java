package com.eebbk.geek.module.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eebbk.geek.R;
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

    public DeviceAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder createHolderView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bluetooth, parent, false);
        return new DeviceVH(view);
    }

    @Override
    protected void bindHolderView(RecyclerView.ViewHolder holder, BluetoothDevice bluetoothDevice, int position) {
        if (holder instanceof DeviceVH) {
            BluetoothDevice item = getItem(position);
            DeviceVH vh = (DeviceVH) holder;
            vh.tvDeviceName.setText(item.getName());
            vh.tvDeviceMac.setText(item.getAddress());
        }
    }

    class DeviceVH extends RecyclerView.ViewHolder{
        TextView  tvDeviceName;
        TextView  tvDeviceMac;

        DeviceVH(@NonNull View itemView) {
            super(itemView);
            tvDeviceName = itemView.findViewById(R.id.tv_device_name);
            tvDeviceMac = itemView.findViewById(R.id.tv_device_mac);
        }
    }
}
