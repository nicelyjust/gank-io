package com.eebbk.geek.module.viewLearn;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eebbk.geek.R;
import com.eebbk.geek.base.fragment.BaseFragment;
import com.eebbk.geek.module.viewLearn.live.LiveHRView;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.module.viewLearn
 *  @文件名:   LiveFragment
 *  @创建者:   lz
 *  @创建时间:  2019/12/18 18:34
 *  @描述：
 */
public class LiveFragment extends BaseFragment implements View.OnClickListener {
    private LiveHRView mLiveHRView;
    private EditText mEditTxt;
    private Button mButton;

    public static LiveFragment newInstance() {
        return new LiveFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initWidget(View root) {
        mLiveHRView = root.findViewById(R.id.hr_v);
        mEditTxt = root.findViewById(R.id.et_value);
        mButton = root.findViewById(R.id.btn_set);
        mButton.setOnClickListener(this);
        //mLiveHRView.setZones(new int[]{56, 115, 145, 190}, new int[]{98, 117, 137, 156, 176, 195});
        mLiveHRView.setZones(new int[]{117, 128,164, 175}, null);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set) {
            String input = mEditTxt.getEditableText().toString();
            if (!TextUtils.isEmpty(input)){
                mLiveHRView.setLiveValue(Integer.parseInt(input));
                mEditTxt.getEditableText().clear();
            }
        }
    }
}
