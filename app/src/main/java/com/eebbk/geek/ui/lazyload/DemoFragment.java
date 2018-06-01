package com.eebbk.geek.ui.lazyload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.LazyBaseFragment;
import com.eebbk.geek.constant.Constant;
import com.eebbk.geek.ui.H5Activity;
import com.eebbk.geek.utils.L;

import butterknife.BindView;
import butterknife.Unbinder;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.ui
 *  @文件名:   DemoFragment
 *  @创建者:   lz
 *  @创建时间:  2017/11/1 11:52
 *  @修改时间:  Administrator 2017/11/1 11:52 
 *  @描述：    TODO
 */
public class DemoFragment extends LazyBaseFragment implements View.OnClickListener {
    private static final String TAG = "DemoFragment";
    private static final String POSITION = "position";
    private static final int CAPTURE_PHOTO_REQUEST_CODE = 0x01;
    /*@BindView(R.id.tv_fragment_name)
    TextView mTvName;*/
    @BindView(R.id.btn_album)
    AppCompatButton mBtnAlbum;
    @BindView(R.id.btn_camera)
    AppCompatButton mBtnCamera;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.js_jump)
    AppCompatButton mJsJump;
    Unbinder unbinder;
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


    @Override
    protected void fetchData() {
        L.d("fetchData", mPosition + ": 加载数据了");
//        mTvName.setText("fragment#:" + mPosition);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initWidget(View root) {
        mJsJump.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);
        mBtnAlbum.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.Extra.DEMO_FOR_RESULT:
                    String stringExtra = data.getStringExtra(Constant.Extra.DEMO_POSITION);
//                    mTvName.setText("i got you" + stringExtra );
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
            case R.id.btn_album:

                break;
            case R.id.btn_camera:
                /*Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory() , "image.jpg");
                Uri fileUri = Uri.fromFile(mPhotoFile);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(captureIntent, CAPTURE_PHOTO_REQUEST_CODE);*/
                break;
            case R.id.js_jump:
                Intent intent = new Intent(mContext, H5Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
