package com.eebbk.geek.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;


/*
 *  @项目名：  gank-io
 *  @包名：    com.eebbk.geek.web
 *  @文件名:   FlutterMainActivity
 *  @创建者:   lz
 *  @创建时间:  2019/7/4 19:05
 *  @描述：
 */
public class FlutterMainActivity extends FlutterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, FlutterMainActivity.class);
        context.startActivity(starter);
    }
}
