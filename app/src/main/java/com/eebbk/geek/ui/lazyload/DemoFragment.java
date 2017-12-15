package com.eebbk.geek.ui.lazyload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.rxLearn.RxJavaActivity;
import com.eebbk.geek.utils.L;

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
public class DemoFragment extends LazyBaseFragment
        implements View.OnClickListener
{
    private static final String TAG      = "DemoFragment";
    private static final String POSITION = "position";
    @BindView(R.id.tv_fragment_name)
    TextView mTvName;
    private String mPosition;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d("DemoFragment", mPosition + ":创建了");
        return super.onCreateView( inflater, container,savedInstanceState);
    }

    @Override
    protected void lazyLoad() {
        L.d("DemoFragment" , mPosition + ": 加载数据了");
        mTvName.setText("fragment#:" + mPosition);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initWidget(View root) {
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
