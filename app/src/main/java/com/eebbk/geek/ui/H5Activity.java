package com.eebbk.geek.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eebbk.geek.R;
import com.eebbk.geek.base.activities.BaseActivity;
import com.eebbk.geek.utils.L;

import butterknife.BindView;


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
    @BindView(R.id.h5_web)
    WebView mWebView;
    @BindView(R.id.tv_title_name)
    TextView mTvName;

    @Override
    protected int getContentView() {
        return R.layout.activity_h5;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mBtnCallJs.setOnClickListener(this);


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
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                L.d("lz" , "title == " + title);
                mTvName.setText(title);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(H5Activity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
        mWebView.addJavascriptInterface(new TestApi(), "test");
    }

    @Override
    protected void initData() {
        super.initData();
        String url = "file:///android_asset/js.html";
        mWebView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        mWebView.loadUrl("javascript:callJS()");
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
