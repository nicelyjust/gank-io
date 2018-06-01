package com.eebbk.geek.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.eebbk.geek.R;
import com.eebbk.geek.base.AppManager;
import com.eebbk.geek.base.activities.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/*
 *  @项目名：  Pad_VideoTraining 
 *  @包名：    com.eebbk.vtraining.banner
 *  @文件名:   H5Activity
 *  @创建者:   lz
 *  @创建时间:  2018/6/1 15:09
 *  @描述：    TODO
 */

public class H5Activity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.btn_call_js)
    Button mBtnCallJs;

    private String mUrl;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_h5;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mBtnCallJs.setOnClickListener(this);
        mWebView = new WebView(AppManager.getsAppContext());

        mLl.addView(mWebView);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        mWebView.addJavascriptInterface(new TestApi(), "test");

    }

    @Override
    protected void initData() {
        super.initData();
        mUrl = "file:///assets/js.html";
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onClick(View v) {
        mWebView.loadUrl("js:callJS()");
    }

    class TestApi {
        @JavascriptInterface
        public String hello() {
            return "android端获取到的";
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewGroup parent = (ViewGroup) mWebView.getParent();
        if (parent != null) {
            parent.removeView(mWebView);
            mWebView.destroy();
        }
    }
}
