package com.eebbk.geek.live;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eebbk.geek.R;

/*
 *  @项目名：  EasyChart
 *  @包名：    com.github.iron.easychart
 *  @文件名:   LiveActivity
 *  @创建者:   lz
 *  @创建时间:  2019/11/20 18:34
 *  @描述：
 */
public class LiveActivity extends AppCompatActivity implements View.OnClickListener {

    private LiveHRView mLiveHRView;
    private EditText mEditTxt;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mLiveHRView = findViewById(R.id.hr_v);
        mEditTxt = findViewById(R.id.et_value);
        mButton = findViewById(R.id.btn_set);
        mButton.setOnClickListener(this);
        mLiveHRView.setZones(new int[]{56, 115, 145, 190}, new int[]{98, 117, 137, 156, 176, 195});
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

    public static void start(Context context) {
        Intent starter = new Intent(context, LiveActivity.class);
        context.startActivity(starter);
    }
}
